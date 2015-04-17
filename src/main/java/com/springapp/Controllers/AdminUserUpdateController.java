package com.springapp.controllers;


import com.springapp.entity.Computer;
import com.springapp.entity.User;
import com.springapp.service.Support;
import com.springapp.service.computerService.ComputerService;
import com.springapp.service.userService.UserService;
import com.springapp.validators.UpdateValidator;
import com.springapp.forms.UserForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashSet;
import java.util.Set;

@Controller
public class AdminUserUpdateController {

    //Log4j
    private static final Logger logger = Logger.getLogger(AdminUserUpdateController.class);

    public AdminUserUpdateController() {
    }

    public AdminUserUpdateController(UpdateValidator updateValidator) {
        this.updateValidator = updateValidator;
    }

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Qualifier("computerServiceImpl")
    @Autowired
    private ComputerService computerService;

    @Qualifier("updateValidator")
    @Autowired
    private UpdateValidator updateValidator;

    @Qualifier("support")
    @Autowired
    private Support support;

    @InitBinder("userForm")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(updateValidator);

//        binder.registerCustomEditor(Set.class, "computers", new ComputerCollector(Set.class) {
//        });  }
        binder.registerCustomEditor(Set.class, "computers", new CustomCollectionEditor(Set.class) {

            @Override
            protected Object convertElement(Object element) {
                String pcName = null;
                Set<Computer> computerSet = new LinkedHashSet<>();

                if (element instanceof String && !((String) element).equals("")) {
                    pcName = (String) element;
                    computerSet.add(computerService.getComputerByName(pcName));
                    new UserForm().setComputers(computerSet);
                }
                if (element instanceof String && ((String) element).equals("Delete")) {
                    computerSet.clear();
                    new UserForm().setComputers(computerSet);
                }
                return pcName != null ? computerService.getComputerByName(pcName) : null;
            }
        });
    }

    @RequestMapping(value = "/adminEdit/{userId}", method = RequestMethod.GET)
    public String updateView(@PathVariable("userId") Integer userId,
                             UserForm userForm,
                             ModelMap model) {
        User userForUpdate = userService.getUserById(userId);
        userForm.setUser(userForUpdate);
        model.addAttribute("userForUpdate", userForUpdate);
        model.addAttribute("userForm", userForm);
        model.addAttribute("computers", computerService.getAllComputers());
        logger.info("Go to Admin's User update Page");
        return "adminUserUpdate";
    }

    @RequestMapping(value = "adminEdit.do/{userId}", method = RequestMethod.POST)
    public ModelAndView updateUserProcess(@ModelAttribute(value = "userForm")
                                          @PathVariable("userId") Integer userId,
                                          Model model,
                                          UserForm userForm,
                                          BindingResult result) {
        User userForUpdate = userService.getUserById(userId);
        model.addAttribute("userForm", userForm);
        model.addAttribute("userForUpdate", userForUpdate);
        model.addAttribute("computers", computerService.getAllComputers());
        updateValidator.validate(userForm, result);

        if (result.hasErrors()) {
            logger.error("Validation error while updating User - " + userForm.getLogin());

            return new ModelAndView("adminUserUpdate");
        }
        return updatingUser(userForUpdate, model, userForm);
    }

    private ModelAndView updatingUser(User userForUpdate, Model model,
                                      UserForm userForm) {
        if (support.isEmailExists(userForm, userForUpdate)) {
            logger.error("Can't update user - not unique email!!");
            model.addAttribute("errorMsg", "Email is already in use!");
            return new ModelAndView("adminUserUpdate");
        }
        support.fillForm(userForm, userForUpdate);
        userForUpdate = userForm.getUser();
        userService.updateUser(userForUpdate);
        logger.info("User " + userForm.getLogin() + " is updated by Admin!");
        return new ModelAndView("redirect:/adminPage");
    }



}

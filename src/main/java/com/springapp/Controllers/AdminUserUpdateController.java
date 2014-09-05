package com.springapp.Controllers;


import com.springapp.Entity.Computer;
import com.springapp.Entity.User;
import com.springapp.Service.ComputerService.ComputerService;
import com.springapp.Service.RoleService.RoleService;
import com.springapp.Service.UserService.UserService;
import com.springapp.Validators.UpdateValidator;
import com.springapp.mvc.UserForm;
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

    @Qualifier("roleServiceImpl")
    @Autowired
    private RoleService roleService;

    @Qualifier("updateValidator")
    @Autowired
    private UpdateValidator updateValidator;

    @InitBinder("userForm")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(updateValidator);

//        binder.registerCustomEditor(Set.class, "computers", new ComputerCollector(Set.class) {
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
//                    UserForm userForm = new UserForm();
//                    userForm.setComputers(computerSet);
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
        if (isEmailExists(userForm, userForUpdate)) {
            logger.error("Can't update user - not unique email!!");
            model.addAttribute("errorMsg", "Email is already in use!");
            return new ModelAndView("adminUserUpdate");
        }
        fillForm(userForm, userForUpdate);
        userForUpdate = userForm.getUser();
        userService.updateUser(userForUpdate);
        logger.info("User " + userForm.getLogin() + " is updated by Admin!");
        return new ModelAndView("redirect:/adminPage");
    }

    private void fillForm(UserForm userForm, User user) {
        userForm.setUserId(user.getUserId());
        userForm.setLogin(user.getLogin());
        userForm.setRegDate(user.getRegDate());
        userForm.setRole(roleService.findByName(user.getRole().getRoleName()));
    }

    /**
     * Compares if Email in the userFrom is the same as authorized userForUpdate's
     * and such userForUpdate exists in the DataBase it returns true.
     *
     * @param userForm    the form from JSP with entered data;
     * @param currentUser the authorized userForUpdate whose data is updating;
     */

    private boolean isEmailExists(UserForm userForm, User currentUser) {
        String enteredEmail = userForm.getEmail();
        User inDBaseExistingUser = userService.getUserByEmail(enteredEmail);
        return inDBaseExistingUser != null
                && enteredEmail.equals(inDBaseExistingUser.getEmail())
                && !enteredEmail.equals(currentUser.getEmail());
    }
}

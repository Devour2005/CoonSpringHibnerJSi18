package com.springapp.Controllers;


import com.springapp.Entity.Computer;
import com.springapp.Entity.User;
import com.springapp.Service.ComputerService.ComputerService;
import com.springapp.Service.RoleService.RoleService;
import com.springapp.Service.UserService.UserService;
import com.springapp.Validators.UpdateValidator;
import com.springapp.mvc.UserForm;
import org.apache.commons.collections.ListUtils;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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
//        User user = userService.getUserById(userId);
        userForm.setUser(userService.getUserById(userId));
        model.addAttribute("userForm", userForm);
        model.addAttribute("computers", computerService.getAllComputers());
        logger.info("Go to Admin's User update Page");
        return "adminUserUpdate";
    }

    @RequestMapping(value = "adminEdit.do/{userId}", method = RequestMethod.POST)
    public ModelAndView updateUserProcess(@ModelAttribute(value = "userForm")
                                          UserForm userForm,
                                          @PathVariable("userId") Integer userId,
                                          BindingResult result, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("computers", computerService.getAllComputers());
        model.addAttribute("userForm", userForm);
        updateValidator.validate(userForm, result);

        if (result.hasErrors()) {
            logger.error("Validation error");
            return new ModelAndView("adminUserUpdate");
        }
//        return updatingUser(user, model, userForm, computer);
        return updatingUser(user, model, userForm);
    }

    /*    private ModelAndView updatingUser(User user, Model model,
                                          UserForm userForm, Set<Computer> computer) { */
    private ModelAndView updatingUser(User user, Model model,
                                      UserForm userForm) {
        if (isEmailExists(userForm, user)) {
            logger.error("Can't update user - not unique email!!");
            model.addAttribute("errorMsg", "Email is already in use!");
            return new ModelAndView("adminUserUpdate");
        }
//        fillForm(userForm, user, computer);
        fillForm(userForm, user);
        user = userForm.getUser();
        userService.updateUser(user);
        logger.info("User " + userForm.getLogin() + " is updated by Admin!");
//        return new ModelAndView("redirect:/adminPage", "user", user);
        return new ModelAndView("redirect:/adminPage");
    }

    //    private void fillForm(UserForm userForm, User user, Set<Computer> computer) {
    private void fillForm(UserForm userForm, User user) {
        userForm.setUserId(user.getUserId());
        userForm.setLogin(user.getLogin());
        userForm.setRegDate(user.getRegDate());
//        userForm.setComputers(computer);
        userForm.setRole(roleService.findByName(user.getRole().getRoleName()));
    }

    /**
     * Compares if Email in the userFrom is the same as authorized user's
     * and such user exists in the DataBase it returns true.
     *
     * @param userForm    the form from JSP with entered data;
     * @param currentUser the authorized user whose data is updating;
     */

    private boolean isEmailExists(UserForm userForm, User currentUser) {
        String enteredEmail = userForm.getEmail();
        User inDBaseExistingUser = userService.getUserByEmail(enteredEmail);
        return inDBaseExistingUser != null
                && enteredEmail.equals(inDBaseExistingUser.getEmail())
                && !enteredEmail.equals(currentUser.getEmail());
    }
}

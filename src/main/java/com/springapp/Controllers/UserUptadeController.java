package com.springapp.controllers;

import com.springapp.entity.Computer;
import com.springapp.entity.User;
import com.springapp.service.computerService.ComputerService;
import com.springapp.service.roleService.RoleService;
import com.springapp.service.userService.UserService;
import com.springapp.validators.UpdateValidator;
import com.springapp.forms.UserForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
public class UserUptadeController {

    //Log4j
    private static final Logger logger = Logger.getLogger(UserUptadeController.class);

    public UserUptadeController() {
    }

    @Qualifier("updateValidator")
    @Autowired
    private UpdateValidator updateValidator;

    public UserUptadeController(UpdateValidator updateValidator) {
        this.updateValidator = updateValidator;
    }

    @InitBinder("userForm")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(updateValidator);
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

    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
    public String updateView(/*@ModelAttribute(value = "userForm")*/
                             @PathVariable("userId") Integer userId,
                             UserForm userForm,
                             ModelMap model) {
        User user = userService.getUserById(userId);
        userForm.setUser(user);
        model.addAttribute("userForm", userForm);
        return "profileupdate";
    }


    @RequestMapping(value = "/edit.do/{userId}", method = RequestMethod.POST)
    public String updateUserProcess(@ModelAttribute(value = "userForm")
                                    UserForm userForm,
                                    @PathVariable("userId") Integer userId,
                                    BindingResult result, Model model,
                                    HttpSession session,
                                    HttpServletRequest request) {
        User user = userService.getUserById(userId);
        Set<Computer> computer = computerService.getAllComputers();
        request.getSession().setAttribute("computer", computer);
//        User user = (User) session.getAttribute("user");
        session.getAttribute("userForm");
        model.addAttribute("userForm", userForm);
        model.addAttribute("computer", computer);
        updateValidator.validate(userForm, result);

        if (result.hasErrors()) {
            logger.error("Validation error");
            return "profileupdate";
        }
        return updatingUser(userForm, user, model, request);
    }
       /*  if (!updatingUser(userForm, user)) {
            logger.error("User update error!");
            logger.error("Login or Email is already in use!");
            model.addAttribute("errorMsg", "Login or Email is already in use!");
            return "profileupdate";
        }*/

    private String updatingUser(UserForm userForm, User user, Model model, HttpServletRequest request) {
        if (isEmailExists(userForm, user)) {
            logger.error("Can't update user - not unique email!!");
            model.addAttribute("errorMsg", "Email is already in use!");
            return "profileupdate";
        }
        fillForm(userForm, user);
        user = userForm.getUser();
        userService.updateUser(user);
        logger.info("User updated!");
        request.getSession().setAttribute("user", user);

        return "newprofile";
    }

    //Filling form with new data from JSP page
    private void fillForm(UserForm userForm, User user) {
        userForm.setUserId(user.getUserId());
        userForm.setLogin(user.getLogin());
        userForm.setRegDate(user.getRegDate());
        userForm.setComputers(userService.getAllUsersComputers(user.getLogin()));
        userForm.setRole(roleService.findByName(user.getRole().getRoleName()));
    }

    /**
     * Compares if Email in the userFrom is the same as authorized user's mail
     * and such user exists in the DataBase returns true.
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

package com.springapp.controllers;

import com.springapp.entity.User;
import com.springapp.service.roleService.RoleService;
import com.springapp.service.userService.UserService;
import com.springapp.forms.RegisterForm;
import com.springapp.validators.UserValidator;
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

@Controller
//@RequestMapping(value = "/register.do")
public class RegisterController {
    //Log4j
    private static final Logger logger = Logger.getLogger(RegisterController.class);

    public RegisterController() {
    }

    @Qualifier("userValidator")
    @Autowired
    private UserValidator userValidator;

    public RegisterController(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @InitBinder("newUser")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
//        binder.setValidator(new UserValidator());
    }

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Qualifier("roleServiceImpl")
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerView(ModelMap model) {
        model.addAttribute("newUser", new RegisterForm());
        return "register";
    }

    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public String registerProcess(@ModelAttribute(value = "newUser")
                                 /*@Valid*/ final RegisterForm newUser,
                                  final BindingResult result, Model model,
                                  HttpServletRequest request) {

        model.addAttribute("newUser", newUser);
        userValidator.validate(newUser, result);
        if (result.hasErrors()) {
            logger.error("Register validation error");
            return "register";
        }
        if (!userCreated(newUser, model)) {
            logger.error("New user creation error!");
            logger.error("Login or Email is already in use!");
            model.addAttribute("errorMsg", "Login or Email is already in use!");
            return "register";
        }
        logger.info("New user created!");
        request.getSession().setAttribute("user", newUser);
        return "thankForReg";
    }

    private boolean userCreated(RegisterForm newUser, Model model) {
        User user = newUser.getUser();
        user.setRole(roleService.findByName(newUser.getRole()));
        return userService.insertUser(user);
    }
}

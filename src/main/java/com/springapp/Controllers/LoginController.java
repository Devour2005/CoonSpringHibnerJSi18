package com.springapp.Controllers;


import com.springapp.Entity.Computer;
import com.springapp.Entity.User;
import com.springapp.Service.ComputerService.ComputerService;
import com.springapp.Service.RoleService.RoleService;
import com.springapp.Service.UserService.UserService;
import com.springapp.mvc.LoginForm;
import com.springapp.Validators.LoginValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Set;


@Controller
public class LoginController {
    //Log4j
    private static final Logger logger = Logger.getLogger(LoginController.class);

    public LoginController() {
    }

    @Qualifier("loginValidator")
    @Autowired
    private LoginValidator loginValidator;

    public LoginController(LoginValidator loginValidator) {
        this.loginValidator = loginValidator;
    }

    @InitBinder("loginForm")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(loginValidator);
//        binder.setValidator(new LoginValidator());
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


    /**
     * This method will load the login.jsp page when the application starts
     */
/*    @RequestMapping(value = {"/", "login", "/login"}, method = RequestMethod.GET)
    public ModelAndView loginPage() {
        return new ModelAndView("login", "loginForm", new LoginForm());
    }*/


/*    @RequestMapping(value = {"/", "login"}, method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }*/

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String userWelcomePage(/*@ModelAttribute("loginForm")
                                  LoginForm loginForm,*/
                                  ModelMap model,
                                  HttpSession session) {

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        String role = String.valueOf(authentication.getAuthorities());
        String login = authentication.getName();
        User user = userService.getUserByLogin(login);
        session.setAttribute("user", user);
        model.addAttribute("role", role);
        model.addAttribute("user", user);
        if (!role.contains("admin")) {
            logger.info("Role 'User' - Go to Welcome Page");
            return "welcome";
        } else {
            model.addAttribute("computers", computerService.getAllComputers());
            model.addAttribute("members", userService.getAllUsers());
            logger.info("Role 'Admin' - Go to Admin Page");
            return "administration";
        }
    }
}


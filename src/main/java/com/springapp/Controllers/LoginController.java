package com.springapp.Controllers;


import com.springapp.Entity.Computer;
import com.springapp.Entity.User;
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
    @RequestMapping(value = {"/", "login", "/login"}, method = RequestMethod.GET)
    public String  loginPage() {
        return "login";
    }


    /**
     * This method will be called when the user submits the login details from login.jsp page.
     * If there is any error it will be displayed on the same page, if the user is valid then, will
     * be redirected to welcome page.
     *
     //     * @param loginForm
     //     * @param bindingResult
     //     * @param request
     //     * @return
     */
   /* @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("loginForm")
                              *//*@Valid*//* LoginForm loginForm,
                              BindingResult bindingResult, Model model,
                              HttpServletRequest request, HttpSession session) {
//        BasicConfigurator.configure();
        Authentication authentication;

        new ModelAndView("login", "loginForm", loginForm);
        loginValidator.validate(loginForm, bindingResult);
        try {
            if (bindingResult.hasErrors()) {
                logger.error("Login validation error");
                return new ModelAndView("login", "loginForm", loginForm);
            }

            authentication = SecurityContextHolder.getContext().getAuthentication();
            logger.info("check  authentication!");
            String login = authentication.getName();
            User user = userService.getUserByLogin(login);
            if (user != null) {
                session.setAttribute("user", user);
                if (user.getRole().getRoleName().equals("admin")) {
                    logger.trace("redirect to Admin page!!");
                    model.addAttribute("users", userService.getAllUsers());
                    return new ModelAndView("administration", "users", userService.getAllUsers());
                }


                request.getSession().setAttribute("user", user);
                //Creating a redirection view to welcome page.
                RedirectView redirectView = new RedirectView("welcome", true);
                logger.info("User authorized successfully");
                return new ModelAndView(redirectView);
            } else {
                logger.error("No Such User!!!");
//                    return new ModelAndView("nosuchuser");
//                    TODO: VERSION ERROR SHOWING- REDIRECTION TO LOGIN PAGE WITH CORRESPONDING MESSAGE
                model.addAttribute("errorMsg", "Wrong login or password!");
                return new ModelAndView("login");
            }

        } catch (Exception e) {
            logger.error("Exception in LoginController " + e.getMessage());
            return new ModelAndView("login", "loginForm", loginForm);
        }
    }*/


    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String printWelcome(ModelMap model, Principal principal,  HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        String role = String.valueOf(authentication.getAuthorities());
        String name = principal.getName();
        model.addAttribute("loginForm", new LoginForm());
        if (!role.contains("ROLE_ADMIN")) {
            return "welcome";
        } else {
            model.addAttribute("role", role);
            model.addAttribute("userList", userService.getAllUsers());
            return "administration";
        }
    }




/*


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView login(ModelAndView modelAndView, HttpServletRequest request,
//    public String login(ModelAndView modelAndView, HttpServletRequest request,
                              HttpSession session, BindingResult result) {
        BasicConfigurator.configure();
        logger.trace("check  authentication!");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
         */
/*
            if (user.getRole().getName().equals("ROLE_ADMIN")) {
            logger.trace("redirect to admin!!");
            model.addAttribute("users", userService.findAll());
            return "admin";
        }*//*

        if (result.hasErrors()) {
            return new ModelAndView("login");
        }

        Map<String, String> errorMsg = new HashMap<String, String>();
        if (!checkEmptyField(login)) {
            errorMsg.put("LoginError", "Login can't be empty!");
            request.setAttribute("errorMsg", errorMsg);
        }
        if (!checkEmptyField(password)) {
            errorMsg.put("PasswordError", "Password can't be empty!");
            request.setAttribute("errorMsg", errorMsg);
        }
        if (!errorMsg.isEmpty()) {
            request.setAttribute("errorMsg", errorMsg);
            modelAndView.addObject("login");
//            return modelAndView;
//            return "login";
        } else {
            try {
                User user = userService.authorization(login, password);
                Set<Computer> computer = userService.getAllUsersComputers(login);
                if (user != null) {
                    getSessionUserComp(request, user, computer);
//                    return "welcome";
                    modelAndView.addObject("welcome", user);

                } else {
                    logger.info("No such user!");
//                    modelAndView.addObject("nosuchuser");
//                    return modelAndView;
                    return new ModelAndView("nosuchuser");
//                    modelAndView.addObject("nosuchuser");
//                    throw new LoginException("No such user");     //todo: uncomment
                }
            } catch (Exception e) {
                logger.error("Exception: " + e.getMessage());
                e.printStackTrace();
            }
        }
        logger.trace("redirect to welcomePage!");
//        return "welcome";
        return modelAndView;
    }
*/

    private void getSessionUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(900);
        session.setAttribute("user", user);
    }




   /*@RequestMapping(value = "login")
    public String login(Model model, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        logger.trace("check  authentication!");
        String login = authentication.getName();
        User user = userService.findByLogin(login);
        logger.trace("remove Attributes!!");
        session.removeAttribute("errorMsg");
        session.setAttribute("user", user);
        if (user.getRole().getName().equals("ROLE_ADMIN")) {
            logger.trace("redirect to admin!!");
            model.addAttribute("users", userService.findAll());
            return "admin";
        }
        logger.trace("redirect to hiUser!!");
        session.removeAttribute("invalidCaptcha");
        return "HiUserPage";

   }*/


    public boolean checkEmptyField(String fieldName) {
        return fieldName != null && fieldName.trim().length() > 0;
    }

    private void getSessionUserComp(HttpServletRequest request, User user, Set<Computer> computer) {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(900);
        session.setAttribute("user", user);
        session.setAttribute("computer", computer);
    }
}



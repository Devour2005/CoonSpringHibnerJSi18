package com.springapp.Controllers;

import com.springapp.Calculation.DataInputForm;
import com.springapp.Entity.User;
import com.springapp.Service.ComputerService.ComputerService;
import com.springapp.Service.UserService.UserService;
import com.springapp.mvc.LoginForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {
    private static final Log logger = LogFactory.getLog(MainController.class);

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Qualifier("computerServiceImpl")
    @Autowired
    private ComputerService computerService;

    @RequestMapping(value = "welcome")
    public String userWelcomePage(ModelMap model, Principal principal, HttpSession session) {
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

    @RequestMapping(value = {"/", "login", "/login"}, method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @Secured("admin")
    @RequestMapping(value = "adminPage", method = RequestMethod.GET)
    private ModelAndView adminPage(Model model) {
        logger.info("Go to Admin Page!");
        model.addAttribute("computers", computerService.getAllComputers());
        return new ModelAndView("administration", "members", userService.getAllUsers());
    }

    @Secured("admin")
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam("userId") Integer userId) {
        userService.deleteUser(userId);
        logger.info("User deleted!");
        return new ModelAndView("redirect:/adminPage");
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    private String registerUser() {
        logger.info("Go to Register Page");
        return "register";
    }

    @Secured(value = {"admin", "user"})
    @RequestMapping(value = "calculatePage", method = RequestMethod.GET)
    private ModelAndView piCalculation() {
        logger.info("Go to Calculation Page!");
        return new ModelAndView("calculation", "dataInputForm", new DataInputForm());
    }

    @RequestMapping(value = "loginError", method = RequestMethod.GET)
    private String noSuchUser(Model model) {
        logger.info("No Such User!");
        model.addAttribute("errorMsg", "Wrong Login or Password");
        return "login";
    }

    /*@RequestMapping(value = "loginError", method = RequestMethod.GET)
    private String noSuchUser() {
        logger.info("No Such User!");
        return "nosuchuser";
    }*/
}

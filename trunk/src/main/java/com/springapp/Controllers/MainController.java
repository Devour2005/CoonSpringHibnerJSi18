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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public String userWelcomePage(ModelMap model, Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        String role = String.valueOf(authentication.getAuthorities());
        String login = authentication.getName();
        User user = userService.getUserByLogin(login);
//        User user = (User)authentication.getPrincipal();
//        principal.getName();
//        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("user", user);
        if (!role.contains("ROLE_ADMIN")) {
            logger.info("Role User - Go to Welcome Page");
            return "welcome";
        } else {
            model.addAttribute("role", role);
            model.addAttribute("userList", userService.getAllUsers());
            logger.info("Role Admin Go to Admin Page");
            return "administration";
        }
//        return "welcome";
    }

  /*  @RequestMapping(value = "administration", method = RequestMethod.GET)
    private ModelAndView listUsers(ModelAndView model) {
        return new ModelAndView("administration", "members", userService.getAllUsers());
    }
*/


    @RequestMapping(value = "adminPage", method = RequestMethod.GET)
    private ModelAndView adminPage(Model model) {
        /*List<User> userList = userService.getAllUsers();
        model.addObject("members", userList);*/
        logger.info("Go to Admin Page!");
        model.addAttribute("computers", computerService.getAllComputers());
        return new ModelAndView("administration", "members", userService.getAllUsers());
    }

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

    @RequestMapping(value = "calculatePage", method = RequestMethod.GET)
    private ModelAndView piCalculation() {
        logger.info("Go to Calculation Page!");
        return new ModelAndView("calculation", "dataInputForm", new DataInputForm());
    }

    @RequestMapping(value = "loginError", method = RequestMethod.GET)
    private String noSuchUser() {
        logger.info("No Such User!");
        return "nosuchuser";
    }


}

    /*
    @RequestMapping(value = "/administration", method = RequestMethod.GET)
//    @RequestMapping(value = "/viewall", method = RequestMethod.GET)
    private ModelAndView listUsers(ModelAndView model) {
        *//*List<User> userList = userService.getAllUsers();
        model.addObject("members", userList);*//*
//        return model.addObject("administration", "members", userService.getAllUsers());
        return new ModelAndView("administration", "members", userService.getAllUsers());
    }*/



          /*  @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndexPage() {
        return "login";
    }
*/
 /*   @RequestMapping(value = "login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }*/

   /* @RequestMapping(value = "/login.do")
    public String authorizePage() {
        return "login";
    }*/

   /* @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String updateView(@ModelAttribute(value = "updateForm")
                             UserForm updateForm,
                             HttpSession session) {
        User user = (User) session.getAttribute("user");
        updateForm.setUser(user);
        return "profileupdate";
    }*/




  /*  @RequestMapping(method = RequestMethod.GET)
    public ModelAndView editView(@ModelAttribute(value = "updateForm")
                                 HttpSession session,
                                 @RequestParam String login, Model model) {
        UserForm updateForm = new UserForm();
        updateForm.setUser(userService.getUserByLogin(login));
//        model.addAttribute("updateForm", userForm);
        session.setAttribute("updateForm", updateForm);
        return new ModelAndView("profileupdate", "updateForm", updateForm);
    }*/


  /*  @RequestMapping(value = "showEdit")
    public String showEditPage(@RequestParam String type, HttpSession session,
                               @RequestParam String login, Model model) {

        if (type.equals("edit")) {
            UserForm userForm = new UserForm();
            userForm.setUser(userService.findByLogin(login));
            model.addAttribute("newUser", userForm);
            session.setAttribute("newUser", userForm);
            session.setAttribute("type", type);
            return "edit";
        }

        model.addAttribute("newUser", new UserForm());
        session.setAttribute("newUser", new UserForm());
        session.setAttribute("type", type);
        return "edit";
    }*/


   /* @RequestMapping(value = "/edit.do", method = RequestMethod.GET)
    public ModelAndView registerView(@ModelAttribute(value = "updateForm")
                                     @RequestParam String login) {

        UserForm updateForm = new UserForm();
        updateForm.setUser(userService.getUserByLogin(login));
        return new ModelAndView("profileupdate", "updateForm", updateForm);
    }*/



 /*   public String showEditPage(@RequestParam String type, HttpSession session,
                               @RequestParam String login, Model model) {

        if (type.equals("edit")) {
            UserForm userForm = new UserForm();
            userForm.setUser(userService.findByLogin(login));
            model.addAttribute("newUser", userForm);
            session.setAttribute("newUser", userForm);
            session.setAttribute("type", type);
            return "edit";
        }

        model.addAttribute("newUser", new UserForm());
        session.setAttribute("newUser", new UserForm());
        session.setAttribute("type", type);
        return "edit";
    }*/


  /*  @RequestMapping(value = "/delete")
    private String deleteUser(@RequestParam("userId") Integer userId) {
//        ModelAndView modelAndView = new ModelAndView("redirect:/viewall");
        try {
            userService.deleteUser(userId);
//            String message = "User was successfully deleted.";
//            modelAndView.addObject("message", message);
        } catch (Exception e) {
            logger.error("Can't remove user!!", e);
        }
        return "/viewall";
    }*/


   /* @RequestMapping(value = "/delete",  method = RequestMethod.GET)
    private ModelAndView deleteUser(@RequestParam("userId") Integer userId) {
        ModelAndView modelAndView = new ModelAndView("redirect:/viewall");
        try {
            userService.deleteUser(userId);
//            String message = "User was successfully deleted.";
//            modelAndView.addObject("message", message);
        } catch (Exception e) {
            logger.error("Can't remove user!!", e);
        }
        return modelAndView;
    }*/




   /* @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String userEdit(@PathVariable Integer userId, Model model) {
       User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "adminUserUpdate";
    }*/


   /* @RequestMapping(value = "/{action}/{userId}", method = RequestMethod.GET)
    private ModelAndView deleteAndEditUser(@PathVariable String action, @PathVariable Integer userId, Model model) {
        User user = userService.getUserById(userId);
        if (action.equals("/delete")) {
            userService.deleteUser(userId);
            return new ModelAndView("redirect:/viewall");
        }
        if (action.equals("/edit"))
            model.addAttribute("user", user);
        return new ModelAndView("redirect:/adminUserUpdate");
    }*/



 /*   @RequestMapping(value = "/viewall/{userId}/{action}", method = RequestMethod.GET)
    public String edit(@PathVariable String userId, @PathVariable String action, Model model) {
        User user = userService.getUserById(Integer.parseInt(userId));
        if (action.equals("delete")) {
            userService.deleteUser(Integer.parseInt(userId));
            return "redirect:/viewall";
        }
        if (action.equals("edit"))
            model.addAttribute("user", user);
        return "/adminUserUpdate";
    }*/







   /* @RequestMapping(value = "login.do")
    public String login(ModelAndView modelAndView , HttpServletRequest request, HttpSession session) {
        logger.trace("check  authentication!");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = userService.authorization(login,password);
        session.setAttribute("user", user);

       *//* if (user.getRole().getName().equals("ROLE_ADMIN")) {
            logger.trace("redirect to admin!!");
            model.addAttribute("users", userService.findAll());
            return "admin";
        }*//*
        logger.trace("redirect to welcomePage!");
        return "welcome";
    }*/






   /* @RequestMapping(value="/team/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteTeam(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("home");
        teamService.deleteTeam(id);
        String message = "Team was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }*/





 /*   @RequestMapping(value ="/login.do", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView model, ) {
        User user = new User();
        user = userService.getUserByLogin();
        model.addObject("login", user);
        return model;
    }*/

/*    protected ModelAndView onSubmit(Object command) throws ServletException {
        Login login = (Login) command;
        String name = login.getUsername();
        String prestatement = "Hello";

        ModelAndView modelAndView = new ModelAndView(getSuccessView());
        modelAndView.addObject("name", name);
        return modelAndView;

    }*/



    /* @RequestMapping(value = "/viewall", method = RequestMethod.POST)
    public String adminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "viewall";
    }*/


 /*   @RequestMapping(value = "/viewall", method = RequestMethod.GET)
    public String defaultPage(ModelMap map) {
        return "redirect:/list";
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listUsers(ModelMap map) {

        map.addAttribute("user", new User());
        map.addAttribute("userList", userService.getAllUsers());

        return "editEmployeeList";
    }
    */



        /*

    @RequestMapping(value = "showEdit")
    public String showEditPage(@RequestParam String type, HttpSession session,
                               @RequestParam String login, Model model) {

        if (type.equals("edit")) {
            RegisterForm userBean = new RegisterForm();
            userBean.setUser(userService.getUserByLogin(login));
            model.addAttribute("newUser", userBean);
            session.setAttribute("newUser", userBean);
            session.setAttribute("type", type);
            return "edit";
        }

        model.addAttribute("newUser", new RegisterForm());
        session.setAttribute("newUser", new RegisterForm());
        session.setAttribute("type", type);
        return "edit";
    }

    @RequestMapping(value = "delete")
    public String deleteUser(@RequestParam Integer userId) {
        User user = userService.getUserById(userId);
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            logger.error("Can't remove user!!", e);
        }
        return "redirect: administration";
    }
*/


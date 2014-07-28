package com.springapp.Controllers;

import com.springapp.Entity.User;
import com.springapp.Service.RoleService.RoleService;
import com.springapp.Service.UserService.UserService;
import com.springapp.mvc.RegisterForm;
import com.springapp.Validators.UserValidator;
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







   /* @RequestMapping(value = "/add_article", method = RequestMethod.GET)
    public ModelAndView getAddArticlePage() {
        return new ModelAndView("article")
                .addObject("categoryList", categoryService.getAllCategoriesNames())
                .addObject("articleDTO", new ArticleDTO());
    }

    @RequestMapping(value = "/add_article", params = "amount", method = RequestMethod.GET)
    public ModelAndView getMoreCategoryFields(
            @RequestParam("amount") String amount, ArticleDTO articleDTO) {
        addingFields(amount, articleDTO);
        return new ModelAndView("article").
                addObject("categoryList", categoryService.getAllCategoriesNames())
                .addObject("articleDTO", articleDTO);
    }

    @RequestMapping(value = "/add_article", method = RequestMethod.POST)
    public ModelAndView addArticleToDB(ArticleDTO articleDTO,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("article")
                    .addObject("categoryList", categoryService.getAllCategoriesNames())
                    .addObject("articleDTO", articleDTO);
        } else {
            Article article = fieldSwap(articleDTO);
            categoryService.addArticleInCategory(article);
            return new ModelAndView("done").addObject("article", articleDTO);
        }
    }*/

//    @RequestMapping(method = RequestMethod.GET)
//    public ModelAndView getRegisterPage() {
//        return new ModelAndView("register");
//    }

/*    @RequestMapping(method = RequestMethod.GET)
    public String getForm() {

        model.addAttribute("message", "Hello Mahdi");
        return "hello"; // assume hello.jsp
    }

    @RequestMapping(params = {"submit"}, method = RequestMethod.GET)
    public String printWelcome(ModelMap model, @RequestParam(value = "xx", required = true) String xx) {

    }*/


/*
    @Controller
    public class HomeController {

        @RequestMapping(value = "/", method = RequestMethod.GET)
        public String showHelloPage(Model model) {
            model.addAttribute("person", new Person());
            return "home";
        }

        @RequestMapping(value = "/", method = RequestMethod.POST)
        public String sayHello(Person person, Model model) {
            model.addAttribute("person", person);
            return "home";
        }
    }*/




    /*
    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public ModelAndView processRegister(@ModelAttribute(value = "newUser")
//                                        @RequestParam(value = "newUser", required = false)
                                        @Valid final RegisterForm newUser,
                                        final BindingResult result,
                                        ModelAndView modelAndView,
                                        HttpServletRequest request) {
        if (result.hasErrors()) {
            logger.debug("result has errors!");
            return new ModelAndView("register");
        }

        if (createUser(newUser, modelAndView)) {
            return new ModelAndView("welcome");
        }
        return new ModelAndView("welcome");
    }*/


    /*@RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public ModelAndView processRegister(@ModelAttribute(value = "newUser")
//                                        @RequestParam(value = "newUser", required = false)
                                        @Valid final RegisterForm newUser,
                                        final BindingResult result,
                                        ModelAndView modelAndView,
                                        HttpServletRequest request) {
        if (result.hasErrors()) {
            logger.debug("result has errors!");
            return new ModelAndView("register");
        }

        if (createUser(newUser, modelAndView)) {
            return new ModelAndView("welcome");
        }
        return new ModelAndView("welcome");
    }*/









    /*  @RequestMapping(method = RequestMethod.POST)
      public String addUser(@ModelAttribute("user") User user, BindingResult result) {
          userValidator.validate(user, result);
          if (result.hasErrors())
              return "/register";
          userService.insertUser(user);
          return "redirect:/welcome";
      }*/

  /*  private void putNewUserForm(ModelMap model) {
        RegisterForm newUser = new RegisterForm();
//      model.put("newUser", newUser);
//        model.addAttribute("register", newUser);
        model.addAttribute("newUser", newUser);
    }*/





















   /* @RequestMapping(method = RequestMethod.GET)
    public String signup(ModelMap model) {
       RegisterForm registerForm = new RegisterForm();
        model.put("registerForm", registerForm);
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSignup(RegisterForm registerForm, BindingResult result) {
        userValidator.validate(registerForm, result);
        if (result.hasErrors()) {
            return "register";
        }
        return "welcome";
    }
}
*/

/*//TODO: NEXT
    @RequestMapping(method = RequestMethod.GET)
    public String register(ModelMap model) {
        putNewUserForm(model);
        return "register";
    }

    private void putNewUserForm(ModelMap model) {
        RegisterForm newUser = new RegisterForm();
        model.put("newUser", newUser);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processRegister(@ModelAttribute("newUser") @Valid final RegisterForm newUser,
                                final BindingResult result, Model model,
//                                @RequestParam("recaptcha_challenge_field") String challenge,
//                                @RequestParam("recaptcha_response_field") String response,
                                HttpServletRequest request) {

//        checkCaptcha(result, model, challenge, response, request);

        if (result.hasErrors()) {
            logger.debug("result has errors!");
            return "register";
        }

        if (createUser(newUser, model)) {
            return "welcome";
        }

        return "welcome";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insertUser(@ModelAttribute("newUser")
                                 RegisterForm newUser, Model model, BindingResult result) {
        userService.insertUser(newUser);
        return "redirect:/register";
    }


    private boolean createUser(RegisterForm newUser, Model model) {
        User user = newUser.getUser();
        userService.insertUser(user);
        return true;
    }




    //TODO: NEXT

    @RequestMapping(method = RequestMethod.GET)
    public String viewRegistration(Map<String, Object> model) {
        RegisterForm registerForm = new RegisterForm();
        model.put("registerForm", registerForm);

        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("registerForm") RegisterForm newUser,
                                      Map<String, Object> model, BindingResult result) {
            User user = newUser;
        userService.insertUser(newUser);


        return "RegistrationSuccess";
    }
}*/



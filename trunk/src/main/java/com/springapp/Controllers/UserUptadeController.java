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
//    public ModelAndView registerView(@ModelAttribute(value = "userForm")
    public String updateView(/*@ModelAttribute(value = "userForm")*/
                             @PathVariable("userId") Integer userId,
                             UserForm userForm,
                             HttpSession session,
                             ModelMap model) {
        User user = userService.getUserById(userId);
//        User user = userService.getUserByEmail("user2@g.cc");          //TODO: DELETE! FOR DEBUG ONLY
        userForm.setUser(user);
        model.addAttribute("userForm", userForm);
        return "profileupdate";
//        return new ModelAndView("profileupdate", "userForm", userForm);
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



   /* //Filling form with new data from JSP page
        fillForm(userForm, user);
        user = userForm.getUser();

        try {
            userService.updateUser(user);
            logger.info("User updated!");
            request.getSession().setAttribute("user", user);
            return "newprofile";
        } catch (NotUniqueEmailException e) {
            logger.error("Can't update user - not unique email!!", e);
            model.addAttribute("errorMsg", "Can't update user - not unique email!!!");
            return "profileupdate";
        }*/



    /* //    @RequestMapping(value = "edit", method = RequestMethod.GET)
      @RequestMapping(value = "/edit.do", method = RequestMethod.GET)
      public ModelAndView registerView(@ModelAttribute(value = "userForm")
  //    public UserForm registerView(@ModelAttribute(value = "userForm")
  //                                         UserForm userForm,
                                       @RequestParam String login,
                                       ModelMap model,
                                       HttpSession session,
                                       HttpServletRequest request) {

          UserForm userForm = new UserForm();
          userForm.setUser(userService.getUserByLogin(login));
  //        model.addAttribute("userForm", userForm);
  //        return "profileupdate";
          return new ModelAndView("profileupdate", "userForm", userForm);
  //        return userForm;
      }



    @RequestMapping(value = "/edit", method = RequestMethod.GET)
      public ModelAndView updateView(@ModelAttribute(value = "userForm")
                                           HttpSession session) {
          User user = (User) session.getAttribute("user");
          UserForm userForm = new UserForm();
          userForm.setUser(user);
          return new ModelAndView("profileupdate", "userForm", userForm);
      }





     @RequestMapping(value = "/updateContact", method = RequestMethod.GET)
      public ModelAndView edit(@RequestParam("id") Integer id) {
          ModelAndView mav = new ModelAndView("editContact");
          Contact contact = contactsDAO.getById(id);
          mav.addObject("editContact", contact);
          return mav;
      }

      @RequestMapping(value = "/updateContact", method = RequestMethod.POST)
      public String update(@ModelAttribute("editContact") Contact contact, BindingResult result, SessionStatus status) {
          validator.validate(contact, result);
          if (result.hasErrors()) {
              return "editContact";
          }
          contactsDAO.update(contact);
          status.setComplete();
          return "redirect:viewAllContacts.do";
      }




      @RequestMapping(value = "editserv", method = RequestMethod.POST)
      public @ResponseBody
      String ajaxEditUser(@ModelAttribute("template") Template template,
                          BindingResult bindingResult) {

          Utils utils = new Utils();
          User user = utils.transformation(template);
          user.setId(userDaoService.findByLogin(template.getLogin()).getId());

          if (template.getRole().equalsIgnoreCase("User")) {
              user.setRole(roleDaoService.findByName("User"));
          } else {
              user.setRole(roleDaoService.findByName("Admin"));
          }
          userDaoService.update(user);
          return "Success";
      }
  */

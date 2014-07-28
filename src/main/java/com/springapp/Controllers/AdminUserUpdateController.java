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

    @Qualifier("updateValidator")
    @Autowired
    private UpdateValidator updateValidator;

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

    @RequestMapping(value = "/adminEdit/{userId}", method = RequestMethod.GET)
    public String updateView(
//            @ModelAttribute(value = "userForm")
            UserForm userForm,
            @PathVariable("userId") Integer userId,

//            @PathVariable("pcName") String pcName,
//            @RequestParam("userId") Integer userId,
//                             HttpSession session,
            ModelMap model) {
        User user = userService.getUserById(userId);
        model.addAttribute("computers", computerService.getAllComputers());
        userForm.setUser(user);
        model.addAttribute("userForm", userForm);
        return "adminUserUpdate";
    }


//    @RequestMapping(value = "/adminEdit.do/{userId}/{compId}", method = RequestMethod.POST)
    @RequestMapping(value = "adminEdit.do/{userId}", method = RequestMethod.POST)
    public ModelAndView updateUserProcess(@ModelAttribute(value = "userForm")
                                          UserForm userForm,
                                          @PathVariable("userId") Integer userId,
//                                          @PathVariable("compId") Integer compId,
                                          BindingResult result, Model model,
                                          HttpSession session,
                                          HttpServletRequest request) {
        User user = userService.getUserById(userId);
        model.addAttribute("computers", computerService.getAllComputers());
//        session.getAttribute("userForm");
//        Computer computer = computerService.getComputerById(compId);
        Integer compId = Integer.valueOf(request.getParameter("compId"));
        Set<Computer> computerSet = userForm.getComputers();
//        Computer computer = computerSet.iterator().next();
//        Integer compId = computer.getCompId();
        Computer computer = computerService.getComputerById(compId);
//        model.addAttribute("computer", computer);

        model.addAttribute("userForm", userForm);
        updateValidator.validate(userForm, result);

        if (result.hasErrors()) {
            logger.error("Validation error");
            return new ModelAndView("adminUserUpdate");
        }
        return updatingUser(user, model, userForm);
    }

    private void fillForm(UserForm userForm, User user) {
        userForm.setUserId(user.getUserId());
        userForm.setLogin(user.getLogin());
        userForm.setRegDate(user.getRegDate());
//        userForm.setComputers(userService.getAllUsersComputers(user.getLogin()));
//        userForm.setComputers(computerService.getAllComputers());
        Set<Computer> computerSet = userForm.getComputers();
        Computer computer = computerSet.iterator().next();
//        Computer computer = computerService.getComputerById(compId);


        HashSet<Computer> computerHashSet = new LinkedHashSet<Computer>();
        computerHashSet.add(computer);
        userForm.setComputers(computerHashSet);
//        computerService.getComputerByName(computer.getPcName())
        userForm.setRole(roleService.findByName(user.getRole().getRoleName()));


    }

    private ModelAndView updatingUser(User user, Model model,
                                      UserForm userForm) {
        if (isEmailExists(userForm, user)) {
            logger.error("Can't update user - not unique email!!");
            model.addAttribute("errorMsg", "Email is already in use!");
            return new ModelAndView("adminUserUpdate");
        }
//        model.addAttribute("computers", computerService.getAllComputers());
//        fillForm(userForm, user, computer);
        fillForm(userForm, user);
        user = userForm.getUser();
        userService.updateUser(user);
        logger.info("User updated!");
        return new ModelAndView("redirect:/adminPage");
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

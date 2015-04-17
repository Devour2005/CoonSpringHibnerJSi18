package com.springapp.service;

import com.springapp.entity.User;
import com.springapp.forms.UserForm;
import com.springapp.service.roleService.RoleService;
import com.springapp.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("support")
public class Support {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Qualifier("roleServiceImpl")
    @Autowired
    private RoleService roleService;

    /**
     * Compares if Email in the userFrom is the same as authorized userForUpdate's
     * and such userForUpdate exists in the DataBase it returns true.
     *
     * @param userForm    the form from JSP with entered data;
     * @param currentUser the authorized userForUpdate whose data is updating;
     */

    public boolean isEmailExists(UserForm userForm, User currentUser) {
        String enteredEmail = userForm.getEmail();
        User inDBaseExistingUser = userService.getUserByEmail(enteredEmail);
        return inDBaseExistingUser != null
                && enteredEmail.equals(inDBaseExistingUser.getEmail())
                && !enteredEmail.equals(currentUser.getEmail());
    }


    /**
     * Fills user's from from DTO to Entity
     * @param userForm DTO form
     * @param user user
     */
    public void fillForm(UserForm userForm, User user) {
        userForm.setUserId(user.getUserId());
        userForm.setLogin(user.getLogin());
        userForm.setRegDate(user.getRegDate());
        userForm.setRole(roleService.findByName(user.getRole().getRoleName()));
    }
}

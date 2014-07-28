


/*
package com.springapp;

import com.springapp.Entity.Role;
import com.springapp.Entity.User;
import com.springapp.Service.ComputerService.ComputerServiceImpl;
import com.springapp.Service.RoleService.RoleServiceImpl;
import com.springapp.Service.UserService.UserServiceImpl;
import com.springapp.mvc.UserForm;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.Model;

*/
/**
 * Created with IntelliJ IDEA.
 * User: !Devour
 * Date: 16.05.14
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 *//*

public class SpringTry {

    public static void main(String[] args) {
        System.out.println("load context");
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
        UserServiceImpl userService = (UserServiceImpl) context.getBean("userServiceImpl");

//        insertTry();
//        deleteUser(2);

        update();

        System.out.println(userService.getAllUsers());
        System.out.println();
    }


    private boolean updatingUser(UserForm updateForm, Model model) {
        UserServiceImpl userService = new UserServiceImpl();
        RoleServiceImpl roleService = new RoleServiceImpl();

        User user = updateForm.getUser();

        user.setUserId(userService.getUserByLogin(user.getLogin()).getUserId());
//        user.setRole(roleService.findByName(updateForm.getRole()));

        return userService.updateUser(user);
    }


    private static boolean update() {
        ConfigurableApplicationContext context = null;
        UserServiceImpl userService;
        RoleServiceImpl roleService;
        ComputerServiceImpl computerService;
        try {
            context = new ClassPathXmlApplicationContext("applicationContext.xml");
            userService = (UserServiceImpl) context.getBean("userServiceImpl");
            roleService = (RoleServiceImpl) context.getBean("roleServiceImpl");
            computerService = (ComputerServiceImpl) context.getBean("computerServiceImpl");

            User user = userService.getUserByLogin("user8");

            UserForm updateForm = new UserForm();
            updateForm.setLogin("rrrr");
            updateForm.setName("rrr");
            updateForm.setPassword("rrr");
            updateForm.setEmail("rrrr@tt.tt");

            updateForm.setRegDate(user.getRegDate());
            updateForm.setComputers(userService.getAllUsersComputers(user.getLogin()));
            updateForm.setUserId(userService.getUserByLogin(user.getLogin()).getUserId());
            updateForm.setRole(roleService.findByName(user.getRole().getRoleName()));
//            user.setComputers(userService.getAllUsersComputers(user.getLogin()));

            user = updateForm.getUser();
           */
/* Integer userID = userService.getUserByLogin(user.getLogin()).getUserId();
            user.setUserId(userID);*//*

//            user.setComputers(user.getComputers());

//            user.setRole(roleService.findByName(user.getRole().getRoleName()));


            return userService.updateUser(user);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (context != null) {
                context.close();
            }
        }
        return false;
    }

    private static void deleteUser(Integer userId) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserServiceImpl userService = (UserServiceImpl) context.getBean("userServiceImpl");
        ComputerServiceImpl computerService = (ComputerServiceImpl) context.getBean("computerServiceImpl");
        try {
            userService.deleteUser(userId);
            System.out.println();
            System.out.println(userService.getAllUsers());
            System.out.println();
            System.out.println(computerService.getAllComputers());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void insertTry() {
        ConfigurableApplicationContext context = null;
        try {
            context = new ClassPathXmlApplicationContext("applicationContext.xml");
            User user = new User();
            Role role = new Role();
            role.setRole_id(1);
            role.setRoleName("admin");
            user.setLogin("login");
            user.setName("name");
            user.setPassword("name");
            user.setEmail("name@n.nnn");
            user.setRole(role);

            UserServiceImpl userService = (UserServiceImpl) context.getBean("userServiceImpl");
            userService.insertUser(user);
            System.out.println();
            System.out.println(userService.getAllUsers());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (context != null) {
                context.close();
            }
        }
    }
}


*/

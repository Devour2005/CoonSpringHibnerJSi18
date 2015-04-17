package com.springapp.service.userService;


import com.springapp.entity.Computer;
import com.springapp.entity.User;
import com.springapp.exceptions.LoginException;

import java.util.List;
import java.util.Set;

public interface UserService {
    abstract public User getUserById(int userId);

    abstract public User getUserByLogin(String login);

    abstract public User getUserByEmail(String email);

    abstract public User authorization(String login, String password) throws LoginException;

    abstract public boolean insertUser(User user);

    abstract public void updateUser(User user) /*throws NotUniqueEmailException*/;
//    abstract public boolean updateUser(User user);

    abstract public void deleteUser(Integer userId);
//    abstract public void deleteUser(User user);

    abstract public List<User> getAllUsers();

    abstract public Set<Computer> getAllUsersComputers(String login);

    abstract public boolean userExistanseCheck(User user);

    abstract public boolean emailExistanseCheck(User user);
}

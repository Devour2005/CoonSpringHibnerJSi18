package com.springapp.dao.userDao;

import com.springapp.entity.Computer;
import com.springapp.entity.User;
import com.springapp.exceptions.LoginException;

import java.util.List;
import java.util.Set;

public interface UserDao {

    abstract public User getUserById(int userId);

    abstract public User getUserByLogin(String login);

    abstract public User getUserByEmail(String email);

    abstract public boolean insertUser(User user);

    abstract public void updateUser(User user)/* throws NotUniqueEmailException*/;
//    abstract public boolean updateUser(User user);

    abstract public void deleteUser(Integer userId);
//    abstract public void deleteUser(User user);

    abstract public User authorization(String login, String password) throws LoginException;

    abstract public Set<Computer> getAllUsersComputers(String login);

    abstract public List<User> getAllUsers();

    abstract public boolean isUserExists(User user);

    abstract public boolean isEmailExist(User user);
}

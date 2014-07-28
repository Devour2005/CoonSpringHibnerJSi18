package com.springapp.Service.UserService;


import com.springapp.DAO.UserDao.UserDao;
import com.springapp.Entity.Computer;
import com.springapp.Entity.User;
import com.springapp.Exceptions.LoginException;
import com.springapp.Exceptions.NotUniqueEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {


    @Autowired
    UserDao userDao;

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public User authorization(String login, String password) throws LoginException {
        return userDao.authorization(login, password);
    }

    @Override
    public boolean insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public void updateUser(User user) /*throws NotUniqueEmailException*/{
        userDao.updateUser(user);
    }

    @Override
    /*public void deleteUser(User user) {
        userDao.deleteUser(user);
    }*/
    public void deleteUser(Integer userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public Set<Computer> getAllUsersComputers(String login) {
        return userDao.getAllUsersComputers(login);
    }

    @Override
    public boolean userExistanseCheck(User user) {
        return userDao.isUserExists(user);
    }

    @Override
    public boolean emailExistanseCheck(User user) {
        return userDao.isEmailExists(user);
    }
}

package com.springapp.dao.userDao;

import com.springapp.entity.Computer;
import com.springapp.entity.User;
import com.springapp.exceptions.LoginException;
import com.springapp.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository("UserDaoImpl")
@SuppressWarnings("unchecked")
public class UserDaoImpl extends AbstractUserDao {

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public boolean insertUser(User user) {
        if (isUserExists(user)) {
            return false;
        }
        currentSession().save(user);
        return true;
    }

    @Override
    @Transactional
    public boolean isUserExists(User user) {
        Session session = HibernateUtil.openSession();
        boolean result = true;
        Query query = session.createQuery("SELECT u FROM User u WHERE u.login=? OR u.email=?");
        query.setString(0, user.getLogin());
        query.setString(1, user.getEmail());
        User u = (User) query.uniqueResult();
        if (u == null) {
            result = false;
        }
        return result;
    }


    @Override
    @Transactional
    public boolean isEmailExists(User user) {
        Session session = HibernateUtil.openSession();
        boolean result = true;
        Query query = session.createQuery("SELECT u FROM User u WHERE u.email=?");
        query.setString(0, user.getEmail());
        User u = (User) query.uniqueResult();
        if (u == null) {
            result = false;
        }
        return result;
    }


   /* @Override
    @Transactional
    public boolean updateUser(User user) {
        if (isUserExists(user)) {
            return false;
        }
        currentSession().update(user);
        return true;
    } */


    @Override
    @Transactional
    public void updateUser(User user) /*throws NotUniqueEmailException*/ {
        if (user == null) {
            throw new NullPointerException();
        }
        if (user.getUserId() < 1) {
            throw new IllegalArgumentException();
        }
        if (user.getEmail() == null || user.getEmail().intern().equals("")) {
            throw new IllegalArgumentException();
        }
        currentSession().saveOrUpdate(user);
    }

    @Override
    @Transactional
    public void deleteUser(Integer userId) {
        User user = (User) currentSession().get(User.class, userId);
        currentSession().delete(user);
    }
  /*  public void deleteUser(User user) {
        if (user == null) {
            throw new NullPointerException();
        }
        currentSession().delete(user);
    }*/

    @Override
    @Transactional
    public User authorization(String login, String password) throws LoginException {
        User user = getUserByLogin(login);
        if (user != null && user.getLogin().equals(login) && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
//            throw new LoginException("No Such User!");
        }
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {
        return (User) currentSession().createCriteria(User.class)
                .add(Restrictions.eq("login", login)).uniqueResult();
    }
    /*public User findByLogin(String login) {
        if (login == null || login.intern() == "") {
            throw new IllegalArgumentException();
        }
        Criteria criteria = currentSession().createCriteria(User.class)
                .add(Restrictions.like("login", login));
        if (criteria.list().isEmpty()) {
            return new User();
        }
        return (User) criteria.list().get(0);
    }*/

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return (User) currentSession().createCriteria(User.class)
                .add(Restrictions.eq("email", email)).uniqueResult();
    }

  /*  public User getUserByEmail(String email) {
        if (email == null || email.intern().equals("")) {
            throw new IllegalArgumentException();
        }
        Criteria criteria = currentSession().createCriteria(User.class)
                .add(Restrictions.like("email", email));
        if (criteria.list().isEmpty()) {
            return new User();
        }
        return (User) criteria.list().get(0);
    }*/


    @Override
    @Transactional
    public User getUserById(int userId) {
        return (User) currentSession().createCriteria(User.class)
                .add(Restrictions.eq("userId", userId)).uniqueResult();
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        Criteria criteria = currentSession().createCriteria(User.class);
        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

   /* @Override
    @Transactional
    public List<User> getAllUsers() {
        Session session = HibernateUtil.openSession();
        return session.createQuery("from User").list();
    }*/


    @Override
    @Transactional
    public Set<Computer> getAllUsersComputers(String login) {
        return getAllRecords(login);
    }

    @Transactional
    protected Set<Computer> getAllRecords(String login) {
        User user = getUserByLogin(login);
        return user.getComputers();
    }
}



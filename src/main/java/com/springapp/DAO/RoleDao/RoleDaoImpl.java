package com.springapp.DAO.RoleDao;

import com.springapp.Entity.Role;
import com.springapp.Exceptions.NotUniqueRoleNameException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("RoleDaoImpl")
@SuppressWarnings("unchecked")
public class RoleDaoImpl extends AbstractRoleDao {

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    @Transactional
    public void create(Role role) throws NotUniqueRoleNameException {
        if (findByName(role.getRoleName()).getRoleName() != null) {
            throw new NotUniqueRoleNameException("The name of role not unique!");
        }
        currentSession().save(role);
    }

    @Override
    @Transactional
    public void update(Role role) {
        if (role == null || role.getRoleId() == 0) {
            throw new IllegalArgumentException("Wrong argument! role can't be null or ID = '0'");
        }
        currentSession().update(role);
    }

    @Override
    @Transactional
    public void remove(Role role) {
        if (role == null) {
            throw new NullPointerException();
        }
        currentSession().delete(role);
    }

    @Override
    @Transactional
    public Role findByName(String name) {
        if (name == null) {
            return new Role();
        }
        if (name.intern().equals("")) {
            throw new IllegalArgumentException("name of role can't be = null or ''");
        }
        Criteria criteria = currentSession().createCriteria(Role.class)
                .add(Restrictions.like("roleName", name));
        if (criteria.list().isEmpty()) {
            return new Role();
        }
        return (Role) criteria.list().get(0);
    }

}


package com.springapp.dao.roleDao;

import com.springapp.entity.Role;
import com.springapp.exceptions.NotUniqueRoleNameException;

abstract public class AbstractRoleDao implements RoleDao {
    abstract public void create(Role role) throws NotUniqueRoleNameException;
//            throws NotUniqueRoleNameException, DBSystemException;

    abstract public void update(Role role);
//            throws DBSystemException;

    abstract public void remove(Role role);
//            throws DBSystemException;

    abstract public Role findByName(String name);
}

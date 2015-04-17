package com.springapp.service.roleService;

import com.springapp.dao.roleDao.RoleDao;
import com.springapp.entity.Role;
import com.springapp.exceptions.NotUniqueRoleNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public void create(Role role) throws NotUniqueRoleNameException {
        roleDao.create(role);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public void remove(Role role) {
        roleDao.remove(role);
    }

    @Override
    public Role findByName(String string) {
        return roleDao.findByName(string);
    }

}


package com.springapp.dao.computerDao;

import com.springapp.entity.Computer;
import com.springapp.entity.User;

import java.util.Set;

abstract class AbstractComputerDao implements ComputerDao {
    abstract public Computer getComputerById(Integer compId);

    abstract public Computer getComputerByName(String pcName);

    abstract public boolean insertComputer(Computer computer);

    abstract public boolean updateComputer(Computer computer);

    abstract public void deleteComputer(Integer compId);

    abstract public Set<User> getAllUsersComputers(Integer compId);

    //    abstract public List<Computer> getAllComputers();
    abstract public Set<Computer> getAllComputers();
}


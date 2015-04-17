package com.springapp.service.computerService;

import com.springapp.entity.Computer;
import com.springapp.entity.User;

import java.util.Set;

public interface ComputerService {
    abstract public Computer getComputerById(Integer compId);

    abstract public Computer getComputerByName(String pcName);

    abstract public boolean insertComputer(Computer computer);

    abstract public boolean updateComputer(Computer computer);

    abstract public void deleteComputer(Integer compId);

    //    abstract public List<Computer> getAllComputers();
    abstract public Set<Computer> getAllComputers();

    abstract public Set<User> getAllUsersComputers(Integer compId);
}

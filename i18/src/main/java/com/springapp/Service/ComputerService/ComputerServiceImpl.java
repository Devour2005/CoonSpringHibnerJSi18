package com.springapp.Service.ComputerService;

import com.springapp.DAO.ComputerDao.ComputerDao;
import com.springapp.Entity.Computer;
import com.springapp.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("computerServiceImpl")
public class ComputerServiceImpl implements ComputerService {

//    public ComputerServiceImpl() {
//    }

    @Autowired
    ComputerDao computerDao;

    @Override
    public Computer getComputerById(Integer compId) {
        return computerDao.getComputerById(compId);
    }

    @Override
    public Computer getComputerByName(String pcName) {
        return computerDao.getComputerByName(pcName);
    }

    @Override
    public boolean insertComputer(Computer computer) {
        return computerDao.insertComputer(computer);
    }

    @Override
    public boolean updateComputer(Computer computer) {
        return computerDao.updateComputer(computer);
    }

    @Override
    public void deleteComputer(Integer compId) {
        computerDao.deleteComputer(compId);
    }

    @Override
    public Set<Computer> getAllComputers() {
        return computerDao.getAllComputers();
    }

    @Override
    public Set<User> getAllUsersComputers(Integer compId) {
        return computerDao.getAllUsersComputers(compId);
    }
}

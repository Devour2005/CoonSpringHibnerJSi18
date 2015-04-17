package com.springapp.dao.computerDao;


import com.springapp.entity.Computer;
import com.springapp.entity.User;
import com.springapp.util.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("ComputerDaoImpl")
@SuppressWarnings("unchecked")
public class ComputerDaoImpl extends AbstractComputerDao {
    private static Logger logger = LogManager.getLogger(ComputerDaoImpl.class);

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    @Transactional
    public boolean insertComputer(Computer computer) {
        if (isComputerExists(computer)) {
            return false;
        }
        currentSession().save(computer);
        return true;
    }

    @Transactional
    public boolean isComputerExists(Computer computer) {
        Session session = HibernateUtil.openSession();
        boolean result = true;
        Query query = session.createQuery("SELECT c FROM Computer c WHERE c.pcName=?");
        query.setString(0, computer.getPcName());
        Computer comp = (Computer) query.uniqueResult();
        if (comp == null) {
            result = false;
        }
        return result;
    }


    @Override
    @Transactional
    public boolean updateComputer(Computer computer) {
        if (isComputerExists(computer)) {
            return false;
        }
        currentSession().update(computer);
        return true;
    }

    @Override
    @Transactional
    public void deleteComputer(Integer compId) {
        Computer computer = (Computer) currentSession().get(Computer.class, compId);
        currentSession().delete(computer);
    }

    @Override
    @Transactional
    public Computer getComputerByName(String pcName) {
        return (Computer) currentSession().createCriteria(Computer.class)
                .add(Restrictions.eq("pcName", pcName)).uniqueResult();
    }

    @Override
    @Transactional
    public Computer getComputerById(Integer compId) {
        return (Computer) currentSession().createCriteria(Computer.class)
                .add(Restrictions.eq("compId", compId)).uniqueResult();
    }

    @Override
    @Transactional
    public Set<Computer> getAllComputers() {
        Criteria criteria = currentSession().createCriteria(Computer.class);
        return new HashSet<Computer>(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
    }


    @Override
    @Transactional
    public Set<User> getAllUsersComputers(Integer compId) {
        return getAllRecords(compId);
    }

    @Transactional
    protected Set<User> getAllRecords(Integer compId) {
        Computer computer = getComputerById(compId);
        return computer.getUser();
    }


    public Set<Computer> addCompById(Integer compId) {
        Session session = HibernateUtil.openSession();
        Computer computer = getComputerById(compId);

        Set<Computer> computerSet = null;
        List<Computer> computerList = new ArrayList<Computer>();

        computerList.add(computer);
        computerSet = new HashSet<Computer>(computerList);

        return computerSet;
    }
}

package edu.nju.hostelWorld.dao.impl;

import edu.nju.hostelWorld.dao.BaseDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by 张文玘 on 2017/3/3.
 */
@Repository
public class BaseDAOImpl implements BaseDAO {
    @Autowired
    SessionFactory sessionFactory;

    public Session getSession(){
        try{
            return sessionFactory.getCurrentSession();
        }catch (HibernateException e){
            return sessionFactory.openSession();
        }
    }

}

package edu.nju.hostelWorld.dao.impl;

import edu.nju.hostelWorld.dao.BaseDAO;
import edu.nju.hostelWorld.dao.CustomerDAO;
import edu.nju.hostelWorld.entity.Customer;
import edu.nju.hostelWorld.util.DataUtil;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by 张文玘 on 2017/3/4.
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    BaseDAO baseDAO;


    public Customer save(String phone, String password) {
        Session session = baseDAO.getSession();
        Customer cust = new Customer();

        int id = DataUtil.getRandomId();
        while(findCustById(id)!=null){
            id = DataUtil.getRandomId();
        }

        cust.setUserid(id);
        cust.setPhone(phone);
        cust.setPassword(password);
        cust.setStatus((byte)0);
        session.save(cust);

        return cust;

    }

    public Customer findCustById(int userid) {

        Session session = baseDAO.getSession();
        Customer cust = (Customer) session.get(Customer.class, userid);
        return cust;
    }

    public Customer findCustByPhone(String phone){
        Session session = baseDAO.getSession();
        String hql = "form edu.nju.hostelWorld.entity.Customer as cust where cust.phone="+phone;
        Query query = session.createQuery(hql);
        Customer customer = (Customer) query.getSingleResult();

        return customer;
    }
}

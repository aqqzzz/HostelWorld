package edu.nju.hostelWorld.dao;

import edu.nju.hostelWorld.entity.Customer;

/**
 * Created by 张文玘 on 2017/3/4.
 */
public interface CustomerDAO {

    public Customer save(String phone, String password);
    public Customer findCustById(int id);
    public Customer findCustByPhone(String phone);
}

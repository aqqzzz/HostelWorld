package edu.nju.hostelWorld.service;

import edu.nju.hostelWorld.entity.Customer;

import java.util.Map;

/**
 * Created by 张文玘 on 2017/3/4.
 */
public interface CustomerService {

    public Map<String,Object> register(Customer customer);

    public Map<String, Object> login(String phone, String password);

    public Customer getCustomerById(int id);

    public Map<String, Object> updateCustomer(Customer customer);
}

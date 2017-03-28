package edu.nju.hostelWorld.service.impl;

import edu.nju.hostelWorld.dao.CustomerDAO;
import edu.nju.hostelWorld.dao.HostelDAO;
import edu.nju.hostelWorld.dao.PayDAO;
import edu.nju.hostelWorld.entity.Customer;
import edu.nju.hostelWorld.entity.Hostel;
import edu.nju.hostelWorld.entity.Pay;
import edu.nju.hostelWorld.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 张文玘 on 2017/3/28.
 */
@Service("payService")
public class PayServiceImpl implements PayService{
    @Autowired
    PayDAO payDAO;
    @Autowired
    CustomerDAO customerDAO;
    @Autowired
    HostelDAO hostelDAO;

    public List<Pay> getAllPay() {
        return payDAO.findAll();
    }

    public List<Pay> getPayByCust(int custId) {
        Customer customer = customerDAO.findByUserid(custId);
        return payDAO.findByCustomerByCustId(customer);
    }

    public List<Pay> getPayByHost(int hostId) {
        Hostel hostel = hostelDAO.getOne(hostId);
        return payDAO.findByHostelByHostId(hostel);
    }
}

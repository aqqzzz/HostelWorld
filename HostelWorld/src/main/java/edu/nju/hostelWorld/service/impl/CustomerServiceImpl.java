package edu.nju.hostelWorld.service.impl;

import edu.nju.hostelWorld.dao.CustomerDAO;
import edu.nju.hostelWorld.entity.Customer;
import edu.nju.hostelWorld.service.CustomerService;
import edu.nju.hostelWorld.util.DataUtil;
import edu.nju.hostelWorld.util.ErrorType;
import javafx.scene.chart.PieChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 张文玘 on 2017/3/4.
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerDAO customerDAO;

    public Map<String, Object> register(Customer customer) {
        Map<String,Object> map = new HashMap<String, Object>();

        String phone = customer.getPhone();

        if(customerDAO.findByPhone(phone)!=null){

            map.put("success", false);
            map.put("error", "phone"); //注册时手机已被注册
        }else{
            customer.setStatus(DataUtil.NOT_ACTIVITED);
            customer.setBalance(0.0);
            customer.setConsumpTotal(0.0);
            customer.setPoint(0);

            int id = DataUtil.getRandomId();
            while(customerDAO.findByUserid(id)!=null){
                id = DataUtil.getRandomId();
            }
            customer.setUserid(id);

            System.out.println("分配到的id为"+id);
            customerDAO.save(customer);
            map.put("success", true);
            map.put("cust_id", customer.getUserid());
            map.put("cust_phone", phone);
        }
        return map;

    }

    public Map<String, Object> login(String phone, String password) {
        Map<String, Object> map = new HashMap<String, Object>();


            Customer cust = customerDAO.findByPhone(phone);
            if(cust==null){
                map.put("success", false);
                map.put("error", "phone");//登陆时登陆手机号未找到
            }else if(!cust.getPassword().equals(password)){

                map.put("success", false);
                map.put("error", "password");//登陆时密码错误
            }else{
                map.put("success", true);
                map.put("cust_id", cust.getUserid());
                map.put("cust_phone", cust.getPhone());

            }


        return map;

    }
}

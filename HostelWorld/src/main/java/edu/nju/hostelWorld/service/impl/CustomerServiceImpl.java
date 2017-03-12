package edu.nju.hostelWorld.service.impl;

import edu.nju.hostelWorld.dao.CustomerDAO;
import edu.nju.hostelWorld.entity.Customer;
import edu.nju.hostelWorld.service.CustomerService;
import edu.nju.hostelWorld.util.DataUtil;
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

    public Map<String, Object> register(String phone, String password, String passwordAgain) {
        Map<String,Object> map = new HashMap<String, Object>();


        if(phone.trim().length()==0 || password.trim().length()==0 || passwordAgain.trim().length()==0){
            map.put("success", false);
            map.put("error", "请把信息填写完整");
        }else if(!DataUtil.isPhoneNum(phone)){
            map.put("success", false);
            map.put("error","请填写正确有效的手机号！");
        }else if(!password.equals(passwordAgain)){
            map.put("success", false);
            map.put("error","两次输入的密码不对应！");
        }else if(customerDAO.findByPhone(phone)!=null){

            map.put("success", false);
            map.put("error","这个手机号已经被注册了！");
        }else{
            Customer customer = new Customer();
            customer.setPhone(phone);
            customer.setPassword(password);

            customer.setStatus(DataUtil.NOT_ACTIVITED);

            int id = DataUtil.getRandomId();
            while(customerDAO.findByUserid(id)!=null){
                id = DataUtil.getRandomId();
            }
            customer.setUserid(id);

            customerDAO.save(customer);
            map.put("success", true);
            map.put("cust_id", customer.getUserid());
            map.put("cust_phone", phone);
        }
        return map;

    }

    public Map<String, Object> login(String phone, String password) {
        Map<String, Object> map = new HashMap<String, Object>();

        if(phone.trim().length()==0 || password.trim().length()==0){
            map.put("success", false);
            map.put("error", "请把信息填写完整！");
        }else if(!DataUtil.isPhoneNum(phone)){
            map.put("success", false);
            map.put("error", "请填写正确的手机号！");
        }else{
            Customer cust = customerDAO.findByPhone(phone);
            if(cust==null){
                map.put("success", false);
                map.put("error", "手机号未注册！");
            }else if(!cust.getPassword().equals(password)){

                map.put("success", false);
                map.put("error", "密码输入错误！");
            }else{
                map.put("success", true);
                map.put("cust_id", cust.getUserid());
                map.put("cust_phone", cust.getPhone());

            }
        }

        return map;

    }
}

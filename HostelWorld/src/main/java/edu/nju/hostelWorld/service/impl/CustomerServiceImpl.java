package edu.nju.hostelWorld.service.impl;

import edu.nju.hostelWorld.dao.BankAccountDAO;
import edu.nju.hostelWorld.dao.CustomerDAO;
import edu.nju.hostelWorld.entity.BankAccount;
import edu.nju.hostelWorld.entity.Customer;
import edu.nju.hostelWorld.service.CustomerService;
import edu.nju.hostelWorld.util.DataUtil;
import edu.nju.hostelWorld.util.ErrorType;
import javafx.scene.chart.PieChart;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    BankAccountDAO bankAccountDAO;

    public Map<String, Object> register(Customer customer) {
        Map<String,Object> map = new HashMap<String, Object>();

        String phone = customer.getPhone();

        if(customerDAO.findByPhone(phone)!=null){

            map.put("success", false);
            map.put("error", "phone"); //注册时手机已被注册
        }else{
            BankAccount account = new BankAccount();
            account.setId(customer.getBankAccountByBankCard().getId());
            if(bankAccountDAO.findById(account.getId())!=null){
                map.put("success", false);
                map.put("error", "bank_account"); //注册时银行卡号已被绑定
            }else{
                account.setBalance(DataUtil.INITIAL_BALANCE);
                bankAccountDAO.save(account);
                customer.setStatus(DataUtil.NOT_ACTIVITED);
                customer.setBalance(0.0);
                customer.setConsumpTotal(0.0);
                customer.setPoint(0);

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

    public Customer getCustomerById(int id) {
        return customerDAO.findByUserid(id);
    }

    public Map<String, Object> updateCustomer(Customer customer) {
        Map<String, Object> map = new HashMap<String, Object>();

        int id = customer.getUserid();
        Customer cust = customerDAO.findByUserid(id);

        customerDAO.updateNameById(customer.getName(),id);
        customerDAO.updateGenderById(customer.getGender(), id);

        if(customer.getBankAccountByBankCard()!=null){
            BankAccount ba = null;
            String baId = customer.getBankAccountByBankCard().getId();
            if(bankAccountDAO.findById(baId)!=null){
                if(!cust.getBankAccountByBankCard().getId().equals(baId)){
                    map.put("success", false);
                    map.put("error", "bank_account");//银行卡已被绑定
                    return map;
                }else{
                    ba = customer.getBankAccountByBankCard();
                }

            }else{
                ba = new BankAccount();
                ba.setId(customer.getBankAccountByBankCard().getId());
                ba.setBalance(DataUtil.INITIAL_BALANCE);
                bankAccountDAO.save(ba);

            }
            customerDAO.updateBankAccountById(ba, id);
//            if(isFlushed==null){
//                map.put("success",false);
//                map.put("error","update");//更新失败
//            }else{
                map.put("success",true);
                map.put("cust_id", customer.getUserid());
                map.put("cust_phone", customer.getPhone());
//            }

        }



        return map;
    }
}

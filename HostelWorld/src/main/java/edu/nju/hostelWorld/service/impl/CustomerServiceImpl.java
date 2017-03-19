package edu.nju.hostelWorld.service.impl;

import edu.nju.hostelWorld.dao.BankAccountDAO;
import edu.nju.hostelWorld.dao.CustLevelDAO;
import edu.nju.hostelWorld.dao.CustomerDAO;
import edu.nju.hostelWorld.entity.BankAccount;
import edu.nju.hostelWorld.entity.CustLevel;
import edu.nju.hostelWorld.entity.Customer;
import edu.nju.hostelWorld.service.CustomerService;
import edu.nju.hostelWorld.util.DataUtil;
import edu.nju.hostelWorld.util.ErrorType;
import javafx.scene.chart.PieChart;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
    @Autowired
    CustLevelDAO custLevelDAO;

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
            map.put("success",true);
            map.put("cust_id", customer.getUserid());
            map.put("cust_phone", customer.getPhone());

        }



        return map;
    }

    public CustLevel getCustLevel(double consump){
        List<CustLevel> levels = custLevelDAO.findAll();
        CustLevel level = null;
        for(int i = 0; i < levels.size(); i++){
            CustLevel tmp = levels.get(i);
            if(tmp.getConsumpTotal()>consump){
                break;
            }
            level = tmp;
        }
        return level;
    }

    public Map<String, Object> recharge(int id, double money, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        Customer cust = customerDAO.findByUserid(id);
        if(!password.equals(cust.getPassword())){
            map.put("success", false);
            map.put("error","password");//密码输入错误
        }else{
            double balance = cust.getBalance();
            balance = balance+money;
            customerDAO.updateBalance(balance,id);
            if(money>=1000&&cust.getStatus()==DataUtil.NOT_ACTIVITED){
                customerDAO.updateStatus(DataUtil.ACTIVITED, id);
            }
            map.put("success", true);
        }
        return map;
    }

    public Map<String, Object> exchangePoints(int id, int point) {
        Map<String, Object> map = new HashMap<String, Object>();
        Customer cust = customerDAO.findByUserid(id);
        int originPoints = cust.getPoint();
        int i = customerDAO.updatePoints(originPoints-point,id);

        double balance = cust.getBalance()+point/100;
        int j = customerDAO.updateBalance(balance, id);

        map.put("points",i);
        map.put("balance", j);
        map.put("success",true);
        return map;

    }
}

package edu.nju.hostelWorld.controller;


import edu.nju.hostelWorld.entity.Customer;
import edu.nju.hostelWorld.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by 张文玘 on 2017/3/4.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(String phone, String password, HttpSession session){
        Map<String, Object> map = customerService.login(phone,password);
        if((Boolean)map.get("success")){
            session.setAttribute("cust_id",map.get("cust_id"));
            session.setAttribute("cust_phone",map.get("cust_phone"));
        }
        return map;
    }

    @RequestMapping("/register")
    @ResponseBody
    public Map<String, Object> register(String phone, String password, String passwordAgain,HttpSession session){
        Map<String, Object> map = customerService.register(phone, password, passwordAgain);
        if((Boolean) map.get("success")){
            session.setAttribute("cust_id",map.get("cust_id"));
            session.setAttribute("cust_phone", map.get("cust_phone"));
        }
        return map;
    }



}

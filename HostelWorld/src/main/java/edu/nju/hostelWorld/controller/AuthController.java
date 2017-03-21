package edu.nju.hostelWorld.controller;


import edu.nju.hostelWorld.entity.Customer;
import edu.nju.hostelWorld.entity.Hostel;
import edu.nju.hostelWorld.service.CustomerService;
import edu.nju.hostelWorld.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by 张文玘 on 2017/3/4.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    CustomerService customerService;
    @Autowired
    HostelService hostelService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model){
        model.addAttribute("customerLogin", new Customer());
        model.addAttribute("hostelLogin", new Hostel());
        return "login";
    }

    @RequestMapping(value = "/customer/login",method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("customerLogin")Customer customer, BindingResult bindingResult, HttpSession session){
        Map<String, Object> map = customerService.login(customer.getPhone(),customer.getPassword());
        if(bindingResult.hasErrors()){
            return "login";
        }

        if((Boolean)map.get("success")){
            session.setAttribute("cust_id", map.get("cust_id"));
            session.setAttribute("cust_phone", map.get("cust_phone"));
            return "customer/home";
        }else{
            if(map.get("error").equals("phone")){
                bindingResult.rejectValue("phone","phone.error","手机号未注册！");
            }else if(map.get("error").equals("password")){
                bindingResult.rejectValue("password","password.error","密码输入错误！");
            }
            return "login";
        }
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(Model model){
        model.addAttribute("customerNew",new Customer());
        model.addAttribute("hostelNew", new Hostel());
        return "register";
    }

    @RequestMapping(value = "/customer/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("customerNew")Customer customer, BindingResult bindingResult, HttpSession session){

        if(bindingResult.hasErrors()){
            return "register";
        }
        Map<String, Object> map = customerService.register(customer);
        if((Boolean)map.get("success")){
            session.setAttribute("cust_id", map.get("cust_id"));
            session.setAttribute("cust_phone", map.get("cust_phone"));
            return "customer/home";
        }else{
            if(map.get("error").equals("phone")){
                bindingResult.rejectValue("phone","phone.error","手机号已被注册");
            }
            return "register";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        Enumeration em = session.getAttributeNames();
        while(em.hasMoreElements()){
            session.removeAttribute(em.nextElement().toString());
        }
        return "redirect:/auth/login";
    }

    @RequestMapping(value = "/hostel/register", method = RequestMethod.POST)
    public String hostelRegister(@Valid @ModelAttribute("hostelNew")Hostel hostel, BindingResult bindingResult, HttpSession session){
//        if(bindingResult.hasErrors()){
//            return "register";
//        }
        Map<String, Object> map = hostelService.register(hostel);
        if(!(Boolean) map.get("success")){
            if(map.get("error").equals("bank_account")){
                bindingResult.rejectValue("hostBankAccountByBankCard.id","银行卡已被绑定！");
            }
//            return "register";
        }else{
            session.setAttribute("host_id", map.get("host_id"));
            return "hostel/home";
        }
        return "hostel/home";
    }

}

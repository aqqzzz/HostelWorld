package edu.nju.hostelWorld.controller;


import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.org.apache.xpath.internal.operations.Mod;
import edu.nju.hostelWorld.entity.Customer;
import edu.nju.hostelWorld.entity.Hostel;
import edu.nju.hostelWorld.service.CustomerService;
import edu.nju.hostelWorld.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
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

        if(customer.getPhone().equals("admin")){
            bindingResult.rejectValue("phone","phone.error","手机号错误！");
        }

        if(bindingResult.hasErrors()){
            return "login";
        }
        Map<String, Object> map = customerService.login(customer.getPhone(),customer.getPassword());

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
    public String hostelRegister(@Valid @ModelAttribute("hostelNew")Hostel hostel, BindingResult bindingResult, HttpSession session, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("customerNew",new Customer());
            return "register";
        }
        Map<String, Object> map = hostelService.register(hostel);
        if(!(Boolean) map.get("success")){
            if(map.get("error").equals("bank_account")){
                bindingResult.rejectValue("hostBankAccountByBankCard.id","hostBankAccountByBankCard.id.error","银行卡已被绑定！");
            }
            model.addAttribute("customerNew",new Customer());
            return "register";
        }else{
            session.setAttribute("host_id", map.get("host_id"));
            return "hostel/home";
        }
    }

    @RequestMapping(value = "/hostel/login")
    public String hostelLogin(@Valid @ModelAttribute("hostelLogin")Hostel hostel, BindingResult bindingResult, HttpSession session, Model model){

        if(bindingResult.hasErrors()){

            model.addAttribute("customerLogin",new Customer());
            return "login";
        }

        Map<String,Object> map = hostelService.login(hostel.getId(), hostel.getHostPassword());
        if((Boolean) map.get("success")){
            session.setAttribute("host_id", hostel.getId());
            return "hostel/home";
        }else{
            if(map.get("error").equals("id")){
                bindingResult.rejectValue("id","id.error","登陆id号错误");
            }else if(map.get("error").equals("wait")){
                bindingResult.rejectValue("hostName","hostName.error","您的开店申请还在审批中，请耐心等待~");
            }else if(map.get("error").equals("rejected")){
                bindingResult.rejectValue("hostName","hostName.error", "您的开店申请被拒绝，请修改信息后重新申请！");
            }else if(map.get("error").equals("password")){
                bindingResult.rejectValue("hostPassword", "hostPassword.error", "登录密码错误");
            }
            model.addAttribute("customerLogin", new Customer());
            return "login";
        }
    }

    @RequestMapping(value="/manage/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> managerLogin(String hwname, String password, HttpSession session){
        Map<String, Object> map = customerService.login(hwname, password);
        if((Boolean) map.get("success")){
            session.setAttribute("manage_id", map.get("cust_id"));
            session.setAttribute("manage_name",hwname);
        }
        return map;
    }
}

package edu.nju.hostelWorld.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import edu.nju.hostelWorld.entity.CustLevel;
import edu.nju.hostelWorld.entity.Customer;
import edu.nju.hostelWorld.service.CustomerService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by 张文玘 on 2017/3/10.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getUserHome(){
        return "customer/home";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getUserDash(Model model, HttpSession session){
        int id = (Integer)session.getAttribute("cust_id");
        Customer customer = customerService.getCustomerById(id);
        CustLevel level = customerService.getCustLevel(customer.getConsumpTotal());
        model.addAttribute("customer", customer);
        model.addAttribute("custLevel", level);
        return "customer/dashboard";
    }

    @RequestMapping(value="/info", method=RequestMethod.GET)
    public String getUserInfo(Model model,HttpSession session){
        int id = (Integer) session.getAttribute("cust_id");
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customerInfo",customer);
        return "customer/info";
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public String editUserInfo(@Valid @ModelAttribute("customerInfo")Customer customer, BindingResult bindingResult, HttpSession session, Model model){
        if(bindingResult.hasErrors()){
            return "customer/info";
        }
        Map<String,Object> map = customerService.updateCustomer(customer);
        if((Boolean)map.get("success")){
            model.addAttribute("update","success");
        }else{
            if (map.get("error").equals("bank_account")) {
                bindingResult.rejectValue("bankAccountByBankCard.id","bankAccountByBankCard.id.error","该银行卡已被其他用户绑定！");
            }
            model.addAttribute("update","fail");
        }
       return "customer/info";
    }

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public String getValidate(HttpSession session, Model model){
        int id = (Integer)session.getAttribute("cust_id");
        Customer cust = customerService.getCustomerById(id);
        model.addAttribute("customer", cust);
        return "customer/validate";
    }

    @RequestMapping(value = "/recharge",method = RequestMethod.GET)
    public String getRecharge(HttpSession session, Model model){
        int id = (Integer)session.getAttribute("cust_id");
        Customer cust = customerService.getCustomerById(id);
        model.addAttribute("customer", cust);
        return "customer/recharge";
    }

    @RequestMapping(value = "/recharge",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> recharge(HttpSession session, double money, String password){
        int id = (Integer)session.getAttribute("cust_id");
        Map<String, Object> map = customerService.recharge(id, money, password);
        if(!(Boolean) map.get("success")){
            if (map.get("error").equals("password")) {
                map.put("error", "密码输入错误！");
            }
        }
        return map;
    }

    @RequestMapping(value = "/point", method = RequestMethod.GET)
    public String getPoint(HttpSession session, Model model){
        int id = (Integer)session.getAttribute("cust_id");
        Customer cust = customerService.getCustomerById(id);
        CustLevel level = customerService.getCustLevel(cust.getConsumpTotal());
        model.addAttribute("customer", cust);
        model.addAttribute("custLevel", level);
        return "customer/point";
    }

    @RequestMapping(value = "/point", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> exchangePoint(HttpSession session, int point){
        int id = (Integer)session.getAttribute("cust_id");
        Map<String, Object> map = customerService.exchangePoints(id, point);

        return map;

    }

    @RequestMapping(value = "/stop", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> stop(HttpSession session){
        int id= (Integer)session.getAttribute("cust_id");
        Map<String,Object> map = customerService.stop(id);

        return map;
    }
}

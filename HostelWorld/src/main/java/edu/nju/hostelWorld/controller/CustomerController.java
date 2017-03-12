package edu.nju.hostelWorld.controller;

import edu.nju.hostelWorld.entity.Customer;
import edu.nju.hostelWorld.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 张文玘 on 2017/3/10.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/supplyInfo", method = RequestMethod.GET)
    public String getSupplyInfo(Model model){
        model.addAttribute("customerInfo",new Customer());
        return "/customer/supplyInfo";
    }

    @RequestMapping(value = "/supplyInfo", method = RequestMethod.POST)
    public String supplyInfo(@ModelAttribute("customerInfo") Customer customer, Model model){
        return "";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getUserHome(){
        return "customer/home";
    }
}

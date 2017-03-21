package edu.nju.hostelWorld.controller;

import edu.nju.hostelWorld.entity.Customer;
import edu.nju.hostelWorld.entity.Hostel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 张文玘 on 2017/3/4.
 */
@Controller
public class MainController {

    @RequestMapping("")
    public String home(Model model){
        model.addAttribute("customerLogin",new Customer());
        model.addAttribute("hostelLogin", new Hostel());

        return "login";
    }
}

package edu.nju.hostelWorld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 张文玘 on 2017/3/22.
 */
@Controller
@RequestMapping("/hostel")
public class HostelController {

    @RequestMapping("/")
    public String home(){
        return "hostel/home";
    }

}

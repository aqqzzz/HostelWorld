package edu.nju.hostelWorld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 张文玘 on 2017/3/4.
 */
@Controller
public class MainController {

    @RequestMapping("/index")
    public String home(){
        return "index";
    }
}

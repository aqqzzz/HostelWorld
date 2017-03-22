package edu.nju.hostelWorld.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import edu.nju.hostelWorld.dao.ApplyDAO;
import edu.nju.hostelWorld.dao.HostelDAO;
import edu.nju.hostelWorld.entity.Apply;
import edu.nju.hostelWorld.entity.Hostel;
import edu.nju.hostelWorld.service.HostelService;
import edu.nju.hostelWorld.service.ManagerService;
import org.apache.commons.logging.impl.WeakHashtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张文玘 on 2017/3/22.
 */
@Controller
@RequestMapping("/manage")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @Autowired
    HostelService hostelService;


    @RequestMapping("")
    public String home(Model model){
        List<Apply> applyList = managerService.getAllApplyList();
        model.addAttribute("applyList", applyList);
        return "manage/home";
    }

    @RequestMapping(value = "/apply/info",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getHostelInfo(int hostelId){
        Hostel hostel = hostelService.getHostelInfo(hostelId);
        Map<String, Object> map = new HashMap<String,Object>();
        if(hostel!=null){
            map.put("success",true);
            map.put("id",hostel.getId());
            map.put("name",hostel.getHostName());
            map.put("bank_card",hostel.getHostBankAccountByBankCard().getId());
            map.put("tel",hostel.getHostTel());
            map.put("location",hostel.getLocation());
            map.put("description",hostel.getDiscription());

        }else{
            map.put("success",false);
            map.put("error","没有对应客栈！");
        }

        return map;
    }

    @RequestMapping(value = "/apply/accept", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> acceptApply(int id){
        Map<String,Object> map = managerService.acceptApply(id);

        return map;
    }

    @RequestMapping(value = "/apply/reject",method = RequestMethod.POST)
    @ResponseBody
    public void rejectApply(int id){
        managerService.rejectApply(id);
    }
}

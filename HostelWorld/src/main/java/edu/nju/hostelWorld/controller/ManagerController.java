package edu.nju.hostelWorld.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import edu.nju.hostelWorld.dao.ApplyDAO;
import edu.nju.hostelWorld.dao.HostelDAO;
import edu.nju.hostelWorld.entity.*;
import edu.nju.hostelWorld.service.*;
import edu.nju.hostelWorld.util.DataUtil;
import org.apache.commons.logging.impl.WeakHashtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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

    @Autowired
    ReserveService reserveService;

    @Autowired
    PayService payService;





    @RequestMapping("")
    public String home(Model model){
        List<Apply> applyList = managerService.getAllApplyList();
        model.addAttribute("applyList", applyList);
        return "manage/home";
    }

    @RequestMapping(value = "/apply/info",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getHostelInfo(int hostelId){
        Map<String,Object> hostMap = hostelService.getHostelInfo(hostelId);
        Hostel hostel = (Hostel)hostMap.get("hostel");
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

    @RequestMapping(value = "/settlement", method = RequestMethod.GET)
    public String getSettlementPage(Model model){

        return "manage/settlement";
    }

    @RequestMapping(value = "/getSettleList", method = RequestMethod.GET)
    public String getSettleList(Model model){
        List<Settlement> list = reserveService.getAllSettlements();

        List<Settlement> settlements = new ArrayList<Settlement>();
        List<Settlement> solved = new ArrayList<Settlement>();
        for(int i = 0; i < list.size(); i++){
            Settlement settlement = list.get(i);
            if(settlement.getStatus()== DataUtil.WAIT_SETTLEMENT){
                settlements.add(settlement);
            }else{
                solved.add(settlement);
            }
        }

        model.addAttribute("settlementList", settlements);
        model.addAttribute("solvedList", solved);

        return "manage/detail/settleList";
    }

    @RequestMapping(value = "/settle", method = RequestMethod.POST)
    public String settle(int settlementId, double rate, Model model){
        Map<String, Object> map = managerService.acceptSettlement(settlementId, rate);
        return getSettleList(model);
    }

    @RequestMapping(value = "/getHostelRecord",method = RequestMethod.GET)
    public String getHostelRecord(Model model){
        List<Hostel> hostelList = hostelService.getAllHostel();
        model.addAttribute("hostelList", hostelList);
        return "manage/hostelRecord";
    }

    @RequestMapping(value = "/getCustRecord", method = RequestMethod.GET)
    public String getCustRecord(Model model){
        List<Customer> customers = managerService.getAllCustomer();
        model.addAttribute("customerList",customers);
        return "manage/customerRecord";
    }

    @RequestMapping(value = "/getWorldRecord", method = RequestMethod.GET)
    public String getHostelWorldRecord(Model model){
        Customer manager = managerService.getManager();
        List<Pay> payList = payService.getPayByCust(manager.getUserid());
        model.addAttribute("manager",manager);
        model.addAttribute("payList", payList);
        return "manage/record";
    }
}

package edu.nju.hostelWorld.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import edu.nju.hostelWorld.dao.PlanDAO;
import edu.nju.hostelWorld.entity.*;
import edu.nju.hostelWorld.service.*;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 张文玘 on 2017/3/10.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    ReserveService reserveService;
    @Autowired
    PlanService planService;
    @Autowired
    HostelService hostelService;
    @Autowired
    RoomInfoService roomInfoService;

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


    @RequestMapping(value = "/reserve/{planId}/{inTime}/{outTime}", method = RequestMethod.GET)
    public String getReservePage(@PathVariable("planId")int planId, @PathVariable("inTime") Date inTime,
                                 @PathVariable("outTime") Date outTime, Model model){
        Plan plan = planService.getPlan(planId);
        model.addAttribute("plan",plan);
        List<RoomInfo> roomInfos = roomInfoService.getFreeRoom(plan, inTime, outTime);
        model.addAttribute("roomInfos", roomInfos);
        model.addAttribute("inTime",inTime);
        model.addAttribute("outTime",outTime);
        model.addAttribute("betweenDays",(outTime.getTime()-inTime.getTime())/(1000*60*60*24));

        return "customer/reserve";
    }

    @RequestMapping(value = "/reserve",method = RequestMethod.POST)
    public String reserve(@DateTimeFormat(pattern = "yyyy-MM-dd") Date inTime, @DateTimeFormat(pattern = "yyyy-MM-dd")Date outTime, int roomCount, byte payType, int planId, Model model, HttpSession session){
        Plan plan = planService.getPlan(planId);

        List<RoomInfo> roomInfos = roomInfoService.getFreeRoom(plan, inTime, outTime);
        int custId = (Integer) session.getAttribute("cust_id");
        Customer customer = customerService.getCustomerById(custId);

        int betweenDays = (int)(outTime.getTime()-inTime.getTime())/(1000*60*60*24);
        List<Integer> reserveIdList = new ArrayList<Integer>();

        double price = betweenDays*roomCount*plan.getPrice();
        if(roomInfos.size()<roomCount){
            model.addAttribute("error", "预订房间数超过剩余房间数量！");
            return getReservePage(plan.getId(), inTime, outTime, model);
        }else if(price>customer.getBalance()){
            model.addAttribute("error", "卡余额不足，请先充值！");
            return getReservePage(plan.getId(), inTime, outTime, model);
        }else{
            for(int i = 0; i < roomCount; i++){
                RoomInfo roomInfo = roomInfos.get(i);
                Map<String, Object> map = reserveService.reserve(custId, roomInfo.getId(),payType,inTime,outTime);
                if((Boolean) map.get("success")){
                    int reserveId = (Integer) map.get("reserveId");
                    reserveIdList.add(reserveId);
                }
            }
            model.addAttribute("reserveIdList", reserveIdList);
            return "customer/reserveSuccess";
        }
    }

    @RequestMapping(value = "/planBetween", method = RequestMethod.GET)
    public String getPlanBetween(@DateTimeFormat(pattern = "yyyy-MM-dd") Date inTime, @DateTimeFormat(pattern = "yyyy-MM-dd") Date outTime, int hostelId, Model model){

        List<Plan> planList = planService.getPlanByTime(inTime, outTime, hostelId);
        model.addAttribute("planList",planList);
        model.addAttribute("inTime", inTime);
        model.addAttribute("outTime", outTime);

        return "customer/detail/hostelPlan";
    }




}

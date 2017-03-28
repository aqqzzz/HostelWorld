package edu.nju.hostelWorld.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import edu.nju.hostelWorld.entity.*;
import edu.nju.hostelWorld.service.*;
import edu.nju.hostelWorld.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by 张文玘 on 2017/3/22.
 */
@Controller
@RequestMapping("/hostel")
public class HostelController {

    @Autowired
    HostelService hostelService;
    @Autowired
    RoomLevelService roomLevelService;
    @Autowired
    PlanService planService;
    @Autowired
    RoomInfoService roomInfoService;
    @Autowired
    ReserveService reserveService;
    @Autowired
    PayService payService;

    @RequestMapping("/")
    public String home(HttpSession session, Model model){
        int id = (Integer) session.getAttribute("host_id");
        Hostel hostel = (Hostel)hostelService.getHostelInfo(id).get("hostel");

        List<Reserve> list = reserveService.getReserveByHostel(id);

        List<Reserve> reserveList = new ArrayList<Reserve>();
        List<Reserve> cancelList = new ArrayList<Reserve>();
        List<Reserve> checkInList = new ArrayList<Reserve>();
        List<Reserve> checkOutList = new ArrayList<Reserve>();

        for(int i = 0; i < list.size(); i++){
            Reserve reserve = list.get(i);
            if(reserve.getStatus()==1){
                reserveList.add(reserve);
            }else if(reserve.getStatus()==0){
                cancelList.add(reserve);
            }else if(reserve.getStatus()==2){
                checkInList.add(reserve);
            }else{
                checkOutList.add(reserve);
            }
        }

        List<Settlement> settlements = new ArrayList<Settlement>();
        for(int i = 0; i < checkInList.size(); i++){
            Reserve reserve = checkInList.get(i);
            Settlement settlement = reserveService.getSettlementsByReserve(reserve);
            settlements.add(settlement);
        }

        model.addAttribute("settlementList", settlements);

        List<Pay> payList = payService.getPayByHost(id);

        model.addAttribute("balance", hostel.getBalance());
        model.addAttribute("reserveList", reserveList);
        model.addAttribute("cancelList", cancelList);
        model.addAttribute("checkInList",checkInList);
        model.addAttribute("checkOutList", checkOutList);
        model.addAttribute("payList", payList);

        return "hostel/home";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String getHostelInfoPage(){
        return "hostel/info";
    }

    @RequestMapping(value = "/getInfo",method = RequestMethod.GET)
    public String getHostelInfo(HttpSession session, Model model){
        int id = (Integer)session.getAttribute("host_id");
        Map<String,Object> map = hostelService.getHostelInfo(id);
        Hostel hostel = (Hostel)map.get("hostel");

        if((Boolean)map.get("success")){
            model.addAttribute("success",true);
            if(map.get("hint")!=null){
                String hint = (String)map.get("hint");
                if(hint.equals("rejected")){
                    model.addAttribute("hint","rejected");//未通过审批
                }else if(hint.equals("approved")){
                    model.addAttribute("hint","approved");//通过审批
                }
            }
        }else{
            model.addAttribute("success",false);
            model.addAttribute("error",map.get("error"));//正在审批中
        }

        model.addAttribute("hostel",hostel);
        return "hostel/detail/hostelInfo";
    }

    @RequestMapping(value = "/addRoomLevel",method = RequestMethod.POST)
    public String addRoomLevel(@RequestBody RoomLevel roomLevel, HttpSession session, Model model){
        int id = (Integer)session.getAttribute("host_id");
        Map<String,Object> map = hostelService.getHostelInfo(id);

        Hostel hostel = (Hostel)map.get("hostel");
        roomLevel.setHostelByHostelId(hostel);

        //新增
        if(roomLevelService.addRoomLevel(roomLevel)){
            List<RoomLevel> roomLevels = roomLevelService.getAllRoomLevel(id);
            model.addAttribute("roomLevels", roomLevels);
            return "hostel/detail/roomLevelTable";
        }else{
            model.addAttribute("error","添加失败");
            return "hostel/detail/roomLevelTable";
        }

    }

    @RequestMapping(value = "/saveRoomLevel",method = RequestMethod.POST)
    public String saveRoomLevel(@RequestBody RoomLevel roomLevel, HttpSession session, Model model){
        int id = (Integer)session.getAttribute("host_id");
        Map<String,Object> map = hostelService.getHostelInfo(id);

        Hostel hostel = (Hostel)map.get("hostel");

        roomLevel.setHostelByHostelId(hostel);
        //修改
        roomLevelService.changeRoomLevel(roomLevel);
        List<RoomLevel> roomLevels = roomLevelService.getAllRoomLevel(id);
        model.addAttribute("roomLevels",roomLevels);
        return "hostel/detail/roomLevelTable";

    }

    @RequestMapping(value = "/getRoomLevelInfo",method = RequestMethod.GET)
    public String getRoomLevelInfo(HttpSession session, Model model){
        int id = (Integer) session.getAttribute("host_id");
        List<RoomLevel> roomLevels = roomLevelService.getAllRoomLevel(id);
        model.addAttribute("roomLevels", roomLevels);
        return "hostel/detail/roomLevelTable";
    }

    @RequestMapping(value = "/delRoomLevel",method = RequestMethod.POST)
    @ResponseBody
    public void deleteRoomLevel(int roomLevelId){
        roomLevelService.removeRoomLevel(roomLevelId);
    }

    @RequestMapping(value = "/editInfo",method = RequestMethod.POST)
    public String editHostelInfo(HttpSession session, Model model, @RequestBody Hostel hostel){
        Map<String,Object> map = hostelService.editHostelInfo(hostel);
        model.addAttribute("hostel",hostel);
        return "hostel/detail/hostelInfo";
    }

    @RequestMapping(value = "/plan",method = RequestMethod.GET)
    public String getPlanPage(HttpSession session, Model model){
        int id = (Integer)session.getAttribute("host_id");

        return "hostel/plan";
    }

    @RequestMapping(value = "/getPlanInfo", method = RequestMethod.GET)
    public String getPlanInfo(HttpSession session, Model model){
        int id = (Integer) session.getAttribute("host_id");
        List<Plan> planList = planService.getAllPlans(id);
        List<RoomLevel> roomLevels = roomLevelService.getAllRoomLevel(id);
        model.addAttribute(roomLevels);
        model.addAttribute("planList", planList);
        return "hostel/detail/planInfo";
    }

    @RequestMapping(value = "/addPlanInfo",method = RequestMethod.POST)
    public String addPlanInfo(HttpSession session, Model model, @RequestBody Plan plan){
        int id = (Integer)session.getAttribute("host_id");
        Map<String, Object> map = hostelService.getHostelInfo(id);
        Hostel hostel = (Hostel)map.get("hostel");

        plan.setHostelByHostelId(hostel);
        RoomLevel roomLevel = roomLevelService.getRoomLevel(plan.getRoomLevelId());
        plan.setRoomLevelById(roomLevel);

        Map<String,Object> planMap = planService.addPlan(plan);

        if((Boolean) planMap.get("success")){
            //保存成功，设置计划内的roominfo

            Map<String, Object> roomInfoMap = roomInfoService.savePlanRoomInfo(plan);

            return getPlanInfo(session,model);
        }else{
            if(planMap.get("error").equals("expired")){
                model.addAttribute("error","计划开始时间必须晚于今天！");
            }else if(planMap.get("error").equals("days")){
                model.addAttribute("error","计划持续时间不得超过100天！");
            }
            return "hostel/detail/planInfo";
        }

    }

    @RequestMapping(value = "/deletePlanInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deletePlanInfo(int planId){
        Map<String, Object> map = planService.deletePlan(planId);

        if(!(Boolean) map.get("success")){
            if(map.get("error").equals("expired")){
                map.put("error", "此计划已经开始，不能删除！");
            }
        }
        return map;
    }


    @RequestMapping(value = "/getAllHostel", method = RequestMethod.GET)
    public String getAllHostel(Model model){
        List<Hostel> hostelList = hostelService.getAllHostel();
        model.addAttribute("hostelList", hostelList);
        return "customer/hostelList";
    }

    @RequestMapping(value = "/getHostel/{id}",method = RequestMethod.GET)
    public String getHostel(@PathVariable("id") int id, Model model){
        Map<String, Object> map = hostelService.getHostelInfo(id);
        Hostel hostel = (Hostel)map.get("hostel");

        model.addAttribute("hostel",hostel);
        return "customer/hostelSingle";
    }

    @RequestMapping(value = "/getHostelPlan/{id}/{inTime}/{outTime}", method = RequestMethod.GET)
    public String getHostelPlan(@PathVariable("id") int id, @PathVariable("inTime") Date inTime,
                            @PathVariable("outTime") Date outTime, Model model){
        List<Plan> planList = planService.getPlanByTime(inTime,outTime,id);

        model.addAttribute("/planList", planList);
        return "customer/detail/hostelPlan";

    }

    @RequestMapping(value = "/getReserve/{id}", method = RequestMethod.GET)
    public @ResponseBody Reserve getReserve(@PathVariable("id") int id){
        Map<String, Object> map = new HashMap<String, Object>();
        Reserve reserve = reserveService.getReserveInfo(id);
        return reserve;

    }

    @RequestMapping(value = "/getCheckIn", method = RequestMethod.GET)
    public String getCheckInPage(){
        return "hostel/checkin";
    }

    @RequestMapping(value = "/getAddCheckInCust", method = RequestMethod.GET)
    public String getAddCheckInCustPage(int index, Model model){
        model.addAttribute("index",index);
        return "hostel/detail/checkInCust";
    }

    @RequestMapping(value = "/checkIn", method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public Map<String, Object> checkIn(HttpSession session, @RequestBody Map<String, String> custMap){
        String custId = custMap.get("reserveId");
        int id = Integer.parseInt(custMap.get("reserveId"));
        Map<String,String> cmap = new HashMap<String, String>();
        for(String key:custMap.keySet()){
            if(key.equals("reserveId")){
                continue;
            }
            cmap.put(key, custMap.get(key));
        }
        Map<String, Object> map = reserveService.checkInByReserve(id,cmap);

        return map;
    }

    @RequestMapping(value = "/getCheckOutPage", method = RequestMethod.GET)
    public String getCheckOutPage(){
        return "hostel/checkout";
    }

    @RequestMapping(value = "/getCheckInList", method = RequestMethod.GET)
    public String getReserveList(Model model, HttpSession session){
        int hostId = (Integer)session.getAttribute("host_id");
        List<Reserve> reserveList = reserveService.getReserveByHostAndStatus(hostId, DataUtil.CHECK_IN);
        model.addAttribute("reserveList",reserveList);
        return "hostel/detail/checkInList";

    }

    @RequestMapping(value = "/checkOut", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkout(int reserveId){
        Map<String,Object> map = reserveService.checkOutByReserve(reserveId);
        return map;
    }





}

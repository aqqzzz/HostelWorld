package edu.nju.hostelWorld.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import edu.nju.hostelWorld.entity.Hostel;
import edu.nju.hostelWorld.entity.RoomLevel;
import edu.nju.hostelWorld.service.HostelService;
import edu.nju.hostelWorld.service.RoomLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/")
    public String home(){
        return "hostel/home";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String getHostelInfoPage(){
        return "hostel/info";
    }

    @RequestMapping(value = "/getInfo",method = RequestMethod.GET)
    public String getHostelInfo(HttpSession session, Model model){
        int id = (Integer)session.getAttribute("host_id");
        Hostel hostel = hostelService.getHostelInfo(id);

        model.addAttribute("hostel",hostel);
        return "hostel/detail/hostelInfo";
    }

    @RequestMapping(value = "/addRoomLevel",method = RequestMethod.POST)
    public String addRoomLevel(@RequestBody RoomLevel roomLevel, HttpSession session, Model model){
        int id = (Integer)session.getAttribute("host_id");
        Hostel hostel = hostelService.getHostelInfo(id);

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
        Hostel hostel = hostelService.getHostelInfo(id);

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

}

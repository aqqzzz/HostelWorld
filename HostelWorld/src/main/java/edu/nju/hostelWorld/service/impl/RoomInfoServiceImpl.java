package edu.nju.hostelWorld.service.impl;


import edu.nju.hostelWorld.dao.HostelDAO;
import edu.nju.hostelWorld.dao.PlanDAO;
import edu.nju.hostelWorld.dao.RoomInfoDAO;
import edu.nju.hostelWorld.entity.Hostel;
import edu.nju.hostelWorld.entity.Plan;
import edu.nju.hostelWorld.entity.RoomInfo;
import edu.nju.hostelWorld.entity.RoomLevel;
import edu.nju.hostelWorld.service.RoomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 张文玘 on 2017/3/27.
 */
@Service("roomInfoService")
public class RoomInfoServiceImpl implements RoomInfoService{
    @Autowired
    RoomInfoDAO roomInfoDAO;
    @Autowired
    PlanDAO planDAO;
    @Autowired
    HostelDAO hostelDAO;

    public List<RoomInfo> getFreeRoom(Plan plan, Date inTime, Date outTime) {

        List<RoomInfo> roomInfos = new ArrayList<RoomInfo>();
        long betweenDays = (outTime.getTime() - inTime.getTime())/(1000*60*60*24);

        List<RoomInfo> roomInfoList = roomInfoDAO.findByPlanByPlanId(plan);
        for(int j = 0; j < roomInfoList.size(); j++) {
            RoomInfo roomInfo = roomInfoList.get(j);
            String freeTime = roomInfo.getFreeTime();
            Date startTime = plan.getStartTime();
            Date endTime = plan.getEndTime();
            long betweenStartDays = (inTime.getTime() - startTime.getTime()) / (1000 * 60 * 60 * 24);
            int startIndex = (int) betweenStartDays;
            int endIndex = startIndex + (int) betweenDays;

            String subFreeTime = freeTime.substring(startIndex, endIndex);
            if (Integer.parseInt(subFreeTime) == 0) {
                roomInfos.add(roomInfo);
            }
        }



        return roomInfos;
    }

    public Map<String, Object> savePlanRoomInfo(Plan plan) {

        Map<String, Object> map = new HashMap<String, Object>();

        long betweenDays = (plan.getEndTime().getTime()- plan.getStartTime().getTime())/(1000*60*60*24);
        int length = (int)betweenDays;
        String freeTime = String.format("%0"+length+"d", 0);

        Hostel hostel = plan.getHostelByHostelId();

        RoomLevel roomLevel = plan.getRoomLevelById();
        int startRoom = roomLevel.getStartRoomNum();

        //修改计划（必须限定计划还没有开始的时候才能修改），删掉所有该计划下的房间信息，再重新加入
        List<RoomInfo> roomInfos = roomInfoDAO.findByPlanByPlanId(plan);
        if(roomInfos.size()>0){

            roomInfoDAO.delete(roomInfos);
        }


        for(int i = 0; i < roomLevel.getRoomCount(); i++){
            RoomInfo roomInfo = new RoomInfo();
            int roomNum = startRoom+i;
            roomInfo.setRoomLevelId(roomLevel);
            roomInfo.setRoomNum(roomNum);
            roomInfo.setFreeTime(freeTime);
            roomInfo.setHostelByHostelId(hostel);
            roomInfo.setPlanByPlanId(plan);

            roomInfoDAO.save(roomInfo);
        }

        return map;
    }

    private List<Plan> getPlanByTime(int hostelId, Date startTime, Date endTime){
        List<Plan> start = planDAO.findByStartTimeBefore(startTime);
        List<Plan> end = planDAO.findByEndTimeAfter(endTime);

        Hostel hostel = hostelDAO.findOne(hostelId);

        List<Plan> result = new ArrayList<Plan>();

        for(int i = 0; i < start.size(); i++){
            Plan tmp = start.get(i);
            if(!tmp.getHostelByHostelId().equals(hostel)){
                continue;
            }
            if(end.contains(tmp)){
                result.add(tmp);
            }
        }
        return result;
    }
}

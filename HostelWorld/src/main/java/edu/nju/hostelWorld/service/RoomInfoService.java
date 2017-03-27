package edu.nju.hostelWorld.service;

import edu.nju.hostelWorld.entity.Plan;
import edu.nju.hostelWorld.entity.RoomInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 张文玘 on 2017/3/27.
 */
public interface RoomInfoService {

    //找到 对应计划下 符合条件的房间，再根据用户输入的开始时间和结束时间找到对应时间空闲的房间列表
    public List<RoomInfo> getFreeRoom(Plan plan, Date inTime, Date outTime);

    public Map<String, Object> savePlanRoomInfo(Plan plan);

}

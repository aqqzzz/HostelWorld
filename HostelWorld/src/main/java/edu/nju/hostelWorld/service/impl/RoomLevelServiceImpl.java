package edu.nju.hostelWorld.service.impl;

import edu.nju.hostelWorld.dao.HostelDAO;
import edu.nju.hostelWorld.dao.RoomLevelDAO;
import edu.nju.hostelWorld.entity.Hostel;
import edu.nju.hostelWorld.entity.RoomLevel;
import edu.nju.hostelWorld.service.RoomLevelService;
import org.apache.commons.logging.impl.WeakHashtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张文玘 on 2017/3/24.
 */
@Service("roomLevelService")
public class RoomLevelServiceImpl implements RoomLevelService{

    @Autowired
    RoomLevelDAO roomLevelDAO;
    @Autowired
    HostelDAO hostelDAO;


    public List<RoomLevel> getAllRoomLevel(int hostelId) {
        Hostel hostel = hostelDAO.findOne(hostelId);
        List<RoomLevel> roomList = roomLevelDAO.findByHostelByHostelId(hostel);
        return roomList;
    }

    public Boolean addRoomLevel(RoomLevel level){
        RoomLevel savedRoom = roomLevelDAO.save(level);
        if(savedRoom==null){
            return false;
        }else{
            return true;
        }
    }

    public void removeRoomLevel(int id) {
        roomLevelDAO.delete(id);
    }

    public Map<String, Object> changeRoomLevel(RoomLevel roomLevel) {
        Map<String, Object> map = new HashMap<String, Object>();

        RoomLevel rm = roomLevelDAO.save(roomLevel);
        if(rm==null){
            map.put("success",false);
            map.put("error", "null"); //没有对应的roomlevel
        }else{
            map.put("success",true);
        }
        return map;
    }

    public RoomLevel getRoomLevel(int id) {
        return roomLevelDAO.findOne(id);
    }


}

package edu.nju.hostelWorld.service;

import edu.nju.hostelWorld.entity.RoomLevel;

import java.util.List;
import java.util.Map;

/**
 * Created by 张文玘 on 2017/3/24.
 */
public interface RoomLevelService {

    public List<RoomLevel> getAllRoomLevel(int hostelId);

    public Boolean addRoomLevel(RoomLevel level);

    public void removeRoomLevel(int id);

    public Map<String, Object> changeRoomLevel(RoomLevel roomLevel);

    public RoomLevel getRoomLevel(int id);
}

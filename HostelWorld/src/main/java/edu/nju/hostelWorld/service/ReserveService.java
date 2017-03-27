package edu.nju.hostelWorld.service;

import edu.nju.hostelWorld.entity.Reserve;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 张文玘 on 2017/3/27.
 */
public interface ReserveService {

    public Map<String, Object> reserve(int custId, int roomInfoId, byte payType, Date checkIn, Date checkOut);

    public Map<String,Object> checkIn(int reserveId);

    public Map<String, Object> checkOut(int reserveId);

    public List<Reserve> getReserveByCust(int custId);

    public List<Reserve> getReserveByHostel(int hostelId);

    public List<Reserve> getAllReserves();

    public Map<String,Object> cancelReserve(int reserveId);

    public Reserve getReserveInfo(int id);
}

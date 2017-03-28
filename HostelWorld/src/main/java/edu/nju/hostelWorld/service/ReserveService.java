package edu.nju.hostelWorld.service;

import edu.nju.hostelWorld.entity.CheckInCust;
import edu.nju.hostelWorld.entity.Reserve;
import edu.nju.hostelWorld.entity.Settlement;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 张文玘 on 2017/3/27.
 */
public interface ReserveService {

    public Map<String, Object> reserve(int custId, int roomInfoId, byte payType, Date checkIn, Date checkOut);

    public Map<String,Object> checkInByReserve(int reserveId, Map<String, String> custMap);

    public Map<String, Object> checkOutByReserve(int reserveId);

    public Map<String, Object> checkInByCash(CheckInCust checkInCust);

    public Map<String, Object> checkOutByCash(CheckInCust checkInCust);

    public List<Reserve> getReserveByCust(int custId);

    public List<Reserve> getReserveByCustAndStatus(int custId, byte status);

    public List<Reserve> getReserveByHostel(int hostelId);

    public List<Reserve> getReserveByHostAndStatus(int hostelId, byte status);

    public List<Reserve> getAllReserves();

    public Map<String,Object> cancelReserve(int reserveId);

    public Reserve getReserveInfo(int id);

    public Settlement getSettlementsByReserve(Reserve reserve);

    public List<Settlement> getSettlementsByReason(byte reason);

    public List<Settlement> getAllSettlements();
}

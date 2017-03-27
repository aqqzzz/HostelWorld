package edu.nju.hostelWorld.service.impl;

import edu.nju.hostelWorld.dao.*;
import edu.nju.hostelWorld.entity.*;
import edu.nju.hostelWorld.service.ReserveService;
import edu.nju.hostelWorld.util.DataUtil;
import javafx.scene.chart.PieChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 张文玘 on 2017/3/27.
 */
@Service("reserveService")
public class ReserveServiceImpl implements ReserveService{
    @Autowired
    ReserveDAO reserveDAO;
    @Autowired
    CustomerDAO customerDAO;
    @Autowired
    RoomInfoDAO roomInfoDAO;
    @Autowired
    HostelDAO hostelDAO;
    @Autowired
    SettlementDAO settlementDAO;

    //假设这个roomInfoId已经是找到的符合要求的房间信息实体了
    public Map<String, Object> reserve(int custId, int roomInfoId, byte payType, Date checkIn, Date checkOut) {

        Map<String, Object> map = new HashMap<String, Object>();

        Customer customer = customerDAO.findByUserid(custId);

        Customer manager = customerDAO.findByPhone("admin");
        int managerId = manager.getUserid();

        RoomInfo roomInfo = roomInfoDAO.findOne(roomInfoId);
        Plan plan = roomInfo.getPlanByPlanId();
        CustLevel custLevel = customer.getCustLevelById();

        int betweenDays = (int)((checkOut.getTime()-checkIn.getTime())/(1000*60*60*24));

        double price = plan.getPrice()*betweenDays;
        int points = (int)Math.floor(price/100);
        double discount = custLevel.getDiscount();
        double actual = Math.round((price*discount*100)/100);

        double custBalance = customer.getBalance();
        double custConsump = customer.getConsumpTotal();
        if(custBalance<price && payType==DataUtil.PAY_BY_CARD){
            map.put("success",false);
            map.put("error","balance");//余额不足
        }else{

            if(payType==DataUtil.PAY_BY_CARD){
                //更新用户余额
                custBalance-=actual;
                custConsump+=actual;

                customerDAO.updateBalance(custBalance, custId);
                customerDAO.updateConsumpTotal(custConsump, custId);

                //更新管理员余额
                double managerBalance = manager.getBalance()+actual;
                customerDAO.updateBalance(managerBalance, managerId);
            }else{
                actual = price;//现金付款没有优惠
            }


            //持久化保存reserve
            Reserve reserve = new Reserve();
            reserve.setCheckinTime(checkIn);
            reserve.setLeaveTime(checkOut);
            reserve.setOriginal(price);
            reserve.setDiscount(discount);
            reserve.setPoints(points);
            reserve.setActual(actual);
            reserve.setStatus(DataUtil.RESERVE);
            reserve.setPayType(payType);
            reserve.setCustomerByCustId(customer);
            reserve.setRoomInfoById(roomInfo);
            reserveDAO.save(reserve);

            //更新roominfo占用信息
            Date planStart = plan.getStartTime();
            int startIndex = (int)((checkIn.getTime()-planStart.getTime())/(1000*60*60*24));
            int endIndex = startIndex+betweenDays;
            String freeTime = roomInfo.getFreeTime();
            int freeLength = freeTime.length();//总长度

            String allocated = "";
            for(int i = 0; i < betweenDays; i++){
                allocated+="1";
            }

            String newFreeTime = freeTime.substring(0,startIndex)+allocated+freeTime.substring(endIndex, freeLength);
            roomInfoDAO.updateByFreeTime(newFreeTime, roomInfoId);


            map.put("success",true);
            map.put("reserveId", reserve.getId());

        }

        return map;

    }

    public Map<String,Object> checkIn(int reserveId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Reserve reserve = reserveDAO.findOne(reserveId);
        if(reserve==null){
            map.put("success",false);
            map.put("error","null");//找不到对应订单
        }else{
            if(reserve.getStatus()!= DataUtil.RESERVE){
                map.put("success",false);
                map.put("error","status");//当前状态的订单不能入住
            }else{
                //更新订单状态
                Date currentTime = Calendar.getInstance().getTime();
                reserveDAO.updateReserveStatus(DataUtil.CHECK_IN, reserveId);
                reserveDAO.updateReserveActualCheckInTime(currentTime, reserveId);

                if(reserve.getPayType()==DataUtil.PAY_BY_CARD){
                    //申请管理员给客栈结算钱款
                    Settlement settlement = new Settlement();
                    settlement.setReserveByReserveId(reserveDAO.findOne(reserveId));
                    settlement.setAmount(reserve.getActual());
                    settlement.setStatus(DataUtil.WAIT_SETTLEMENT);
                    settlement.setReason(DataUtil.REASON_FINISHED);
                    settlementDAO.save(settlement);
                }


                map.put("success",true);
            }
        }

        return map;
    }

    public Map<String, Object> checkOut(int reserveId){
        Map<String, Object> map = new HashMap<String, Object>();
        Reserve reserve = reserveDAO.findOne(reserveId);

        if(reserve==null){
            map.put("success",false);
            map.put("error","null");//找不到对应订单
        }else{
            if(reserve.getStatus()!= DataUtil.CHECK_IN){
                map.put("success",false);
                map.put("error","status");//当前状态的订单不能离店
            }else{
                //更新订单状态
                Date currentTime = Calendar.getInstance().getTime();
                reserveDAO.updateReserveStatus(DataUtil.CHECK_OUT, reserveId);
                reserveDAO.updateByActualLeaveTime(currentTime, reserveId);
                map.put("success",true);
            }
        }

        return map;
    }

    public List<Reserve> getReserveByCust(int custId) {
        Customer customer = customerDAO.findByUserid(custId);
        List<Reserve> reserves = reserveDAO.findByCustomerByCustId(customer);
        return reserves;
    }

    public List<Reserve> getReserveByHostel(int hostelId) {

        Hostel hostel = hostelDAO.findOne(hostelId);
        List<RoomInfo> roomInfos = roomInfoDAO.findByHostelByHostelId(hostel);

        List<Reserve> reserveList = new ArrayList<Reserve>();
        for(int i = 0; i < roomInfos.size(); i++){
            List<Reserve> tmp = reserveDAO.findByRoomInfoById(roomInfos.get(i));
            reserveList.addAll(tmp);
        }
        return reserveList;
    }

    public List<Reserve> getAllReserves() {
        List<Reserve> reserveList = reserveDAO.findAll();
        return reserveList;
    }

    public Map<String,Object> cancelReserve(int reserveId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Reserve reserve = reserveDAO.findOne(reserveId);

        if(reserve==null){
            map.put("success",false);
            map.put("error","null");//找不到对应的预定订单
        }else{
            if(reserve.getStatus()!= DataUtil.RESERVE){
                map.put("success",false);
                map.put("error","status");//该订单当前状态不是预定状态，不能取消预定
            }else if(reserve.getPayType()==DataUtil.PAY_BY_CASH){
                map.put("success",false);
                map.put("error","payType");//现金状态的订单不能退订
            }else{
                //更新订单状态
                reserveDAO.updateReserveStatus(DataUtil.RESERVE_CANCEL,reserveId);

                //用户和管理员余额更新，在管理员界面拿到对应状态的订单后进行结算，结算后再把钱款结算到对应账户
                Settlement settlement = new Settlement();
                settlement.setReserveByReserveId(reserve);
                settlement.setAmount(reserve.getActual());//客户付的钱，退还比率由管理员设置
                settlement.setReason(DataUtil.REASON_CANCEL); //取消订单原因
                settlement.setStatus(DataUtil.WAIT_SETTLEMENT);
                settlementDAO.save(settlement);


                map.put("success",true);
            }
        }

        return map;

    }

    public Reserve getReserveInfo(int id){
        return reserveDAO.findOne(id);
    }
}

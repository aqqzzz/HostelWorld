package edu.nju.hostelWorld.service.impl;

import edu.nju.hostelWorld.controller.ManagerController;
import edu.nju.hostelWorld.dao.*;
import edu.nju.hostelWorld.entity.*;
import edu.nju.hostelWorld.service.ManagerService;
import edu.nju.hostelWorld.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张文玘 on 2017/3/22.
 */
@Service("managerService")
public class ManagerServiceImpl implements ManagerService{
    @Autowired
    ApplyDAO applyDAO;
    @Autowired
    HostelDAO hostelDAO;
    @Autowired
    SettlementDAO settlementDAO;
    @Autowired
    CustomerDAO customerDAO;
    @Autowired
    CustLevelDAO custLevelDAO;

    public List<Apply> getAllApplyList() {
        List<Apply> applyList = applyDAO.findAll();
        return applyList;
    }

    public Map<String, Object> acceptApply(int id) {
        applyDAO.updateApplyStatus(DataUtil.APPROVED, id);
        Apply apply = applyDAO.getOne(id);
        if(apply.getType()==0) {
            hostelDAO.updateHostelCreateTime(Calendar.getInstance().getTime(), apply.getHostelByHostelId().getId());
        }
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("success",true);
        return map;
    }

    public void rejectApply(int id) {
        applyDAO.updateApplyStatus(DataUtil.NOT_APPROVED, id);
    }

    public List<Settlement> getAllSettlementList() {
        List<Settlement> settlements = settlementDAO.findAll();
        return settlements;
    }

    //总经理结算
    public Map<String, Object> acceptSettlement(int id, double rate) {
        Map<String, Object> map = new HashMap<String, Object>();

        Settlement settlement = settlementDAO.findOne(id);
        Reserve reserve = settlement.getReserveByReserveId();

        double originAmount = settlement.getAmount();
        double actualAmount = originAmount*rate;

        Customer customer = reserve.getCustomerByCustId();
        int custId = customer.getUserid();

        Customer manager = customerDAO.findByPhone("admin");
        int managerId = manager.getUserid();

        if(settlement.getReason()==DataUtil.REASON_CANCEL){
            //客户取消订单的结算

            //更新客户余额和消费等级
            customerDAO.updateBalance(customer.getBalance()+actualAmount, custId);
            customerDAO.updateConsumpTotal(customer.getConsumpTotal()-originAmount, custId);

            //更新管理员余额

            customerDAO.updateBalance(manager.getBalance()-actualAmount, managerId);

        }else if(settlement.getReason()==DataUtil.REASON_FINISHED){
            //交易成功结束，管理员给客栈结算
            Hostel hostel = reserve.getRoomInfoById().getHostelByHostelId();
            //更新客栈余额
            hostelDAO.updateByBalance(hostel.getBalance()+actualAmount, hostel.getId());
            //更新管理员账户余额
            customerDAO.updateBalance(manager.getBalance()-actualAmount, managerId);

            //更新客户等级
            int level = customer.getCustLevelById().getLevel();
            CustLevel custLevel = custLevelDAO.findByLevel(level+1);
            if(customer.getConsumpTotal()>custLevel.getConsumpTotal()){
                customerDAO.updateCustLevelById(getCustLevel(customer.getConsumpTotal()), custId);
            }

        }

        settlementDAO.updateByActualRate(rate, id);
        settlementDAO.updateByStatus(DataUtil.HAVE_SETTLEMENT, id);

        return map;
    }

    private CustLevel getCustLevel(double consump){
        List<CustLevel> levels = custLevelDAO.findAll();
        CustLevel level = null;
        for(int i = 0; i < levels.size(); i++){
            CustLevel tmp = levels.get(i);
            if(tmp.getConsumpTotal()>consump){
                break;
            }
            level = tmp;
        }
        return level;
    }

    public List<Settlement> getSettlementListByStatus(byte status) {
        return settlementDAO.findByStatus(status);
    }


}

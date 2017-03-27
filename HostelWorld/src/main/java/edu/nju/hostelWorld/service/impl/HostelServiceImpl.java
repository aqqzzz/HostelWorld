package edu.nju.hostelWorld.service.impl;

import edu.nju.hostelWorld.dao.ApplyDAO;
import edu.nju.hostelWorld.dao.BankAccountDAO;
import edu.nju.hostelWorld.dao.HostelDAO;
import edu.nju.hostelWorld.dao.PlanDAO;
import edu.nju.hostelWorld.entity.Apply;
import edu.nju.hostelWorld.entity.BankAccount;
import edu.nju.hostelWorld.entity.Hostel;
import edu.nju.hostelWorld.entity.Plan;
import edu.nju.hostelWorld.service.HostelService;
import edu.nju.hostelWorld.util.DataUtil;
import javafx.scene.chart.PieChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 张文玘 on 2017/3/20.
 */
@Service("hostelService")
public class HostelServiceImpl implements HostelService{

    @Autowired
    HostelDAO hostelDAO;
    @Autowired
    BankAccountDAO bankAccountDAO;
    @Autowired
    ApplyDAO applyDAO;
    @Autowired
    PlanDAO planDAO;

    //开店申请
    public Map<String, Object> register(Hostel hostel) {
        Map<String,Object> map = new HashMap<String,Object>();
        String accountId = hostel.getHostBankAccountByBankCard().getId();
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(accountId);
        if(bankAccountDAO.findById(accountId)!=null){
            map.put("success",false);
            map.put("error","bank_account"); //银行卡已被绑定
        }else {
            bankAccount.setBalance(DataUtil.INITIAL_BALANCE);
            bankAccountDAO.save(bankAccount);
            hostel.setHostBankAccountByBankCard(bankAccount);

            int id=DataUtil.getRandomId();
            while(hostelDAO.findOne(id)!=null){
                id = DataUtil.getRandomId();
            }
            hostel.setId(id);

            hostelDAO.save(hostel);

            Apply apply = new Apply();
            apply.setStatus(DataUtil.WAIT);
            apply.setCreateTime(Calendar.getInstance().getTime());
            apply.setHostelByHostelId(hostel);
            apply.setType(DataUtil.CREATE);

            applyDAO.save(apply);

            map.put("success", true);
            map.put("host_id",id);

        }
        return map;


    }

    public Map<String, Object> login(int id, String password) {
        Map<String, Object> map = new HashMap<String, Object>();

        //这个店铺的开店申请一定是列表中的第一个，所以可以直接取其中第一个来进行判断
        Hostel hostel = hostelDAO.findOne(id);
        List<Apply> applyList = applyDAO.findByHostelByHostelId(hostel);
        Apply apply = null;
        if(applyList.size()!=0){
            apply = applyList.get(0);
        }


        if(hostel==null){
            map.put("success",false);
            map.put("error","id"); //登陆id错误
        }else if(!hostel.getHostPassword().equals(password)){
            map.put("success",false);
            map.put("error","password");//登录密码错误
        }else if(apply!=null){
            if(apply.getStatus()==DataUtil.WAIT){
                map.put("success",false);
                map.put("error","wait");//申请中
            }else if(apply.getStatus()==DataUtil.NOT_APPROVED){
                map.put("success",false);
                map.put("error","rejected");//未通过审批
            }else{
                map.put("success",true);
            }
        }else {
            map.put("success",false);
            map.put("error","noRecord");//没有通过开店审批的记录（虽然这个情况不可能出现）
        }

        return map;

    }

    public Map<String, Object> getHostelInfo(int id) {
        Map<String, Object> map = new HashMap<String, Object>();

        Hostel hostel = hostelDAO.findOne(id);
        List<Apply> applyList = applyDAO.findByHostelByHostelId(hostel);
        Apply apply = null;

        if (applyList.size() > 0) {
            Apply tmp = applyList.get(applyList.size() - 1);
            if (tmp.getType() == 1) {
                apply = tmp;
            }
        }

        if (apply != null) {
            Byte status = apply.getStatus();
            if (status == DataUtil.WAIT) {
                map.put("success", false);//申请中状态时不能修改本店信息
                map.put("error", "wait");//申请中
            } else if (status == DataUtil.NOT_APPROVED) {
                map.put("success", true);//申请被拒绝后可以修改本店信息
                map.put("hint", "rejected");//申请被拒绝
            } else if (status == DataUtil.APPROVED) {
                map.put("success", true);
                map.put("hint", "approved");//申请通过
            }
        }else{
            map.put("success",true);
        }

        map.put("hostel", hostel);
        return map;
    }

    public Map<String,Object> editHostelInfo(Hostel hostel) {
        Map<String,Object> map = new HashMap<String,Object>();

        Hostel origin = hostelDAO.findOne(hostel.getId());
        hostel.setHostBankAccountByBankCard(origin.getHostBankAccountByBankCard());

        hostelDAO.save(hostel);

        Apply apply = new Apply();
        apply.setHostelByHostelId(hostel);
        apply.setType(DataUtil.EDIT);
        apply.setStatus(DataUtil.WAIT);
        apply.setCreateTime(Calendar.getInstance().getTime());

        applyDAO.save(apply);

        map.put("success",true);
        return map;
    }

    public List<Hostel> getAllHostel() {
        List<Hostel> hostelList = hostelDAO.findAll();
        return hostelList;
    }


}

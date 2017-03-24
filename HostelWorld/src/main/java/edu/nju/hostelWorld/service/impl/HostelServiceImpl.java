package edu.nju.hostelWorld.service.impl;

import edu.nju.hostelWorld.dao.ApplyDAO;
import edu.nju.hostelWorld.dao.BankAccountDAO;
import edu.nju.hostelWorld.dao.HostelDAO;
import edu.nju.hostelWorld.entity.Apply;
import edu.nju.hostelWorld.entity.BankAccount;
import edu.nju.hostelWorld.entity.Hostel;
import edu.nju.hostelWorld.service.HostelService;
import edu.nju.hostelWorld.util.DataUtil;
import javafx.scene.chart.PieChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
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

        Hostel hostel = hostelDAO.findOne(id);
        Apply apply = applyDAO.findByHostelByHostelId(hostel);
        if(hostel==null){
            map.put("success",false);
            map.put("error","id"); //登陆id错误
        }else if(apply.getStatus()==DataUtil.WAIT){
            map.put("success",false);
            map.put("error","wait");//申请中
        }else if(apply.getStatus()==DataUtil.NOT_APPROVED){
            map.put("success",false);
            map.put("error","rejected");//未通过审批
        }else if(!hostel.getHostPassword().equals(password)){
            map.put("success",false);
            map.put("error","password");//登录密码错误
        }else{
            map.put("success",true);
        }
        return map;

    }

    public Hostel getHostelInfo(int id) {
        Hostel hostel = hostelDAO.findOne(id);
        return hostel;
    }

    public Map<String,Object> editHostelInfo(Hostel hostel) {
        Map<String,Object> map = new HashMap<String,Object>();
        hostelDAO.save(hostel);
        return map;
    }


}

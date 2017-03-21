package edu.nju.hostelWorld.service.impl;

import edu.nju.hostelWorld.dao.BankAccountDAO;
import edu.nju.hostelWorld.dao.HostelDAO;
import edu.nju.hostelWorld.entity.BankAccount;
import edu.nju.hostelWorld.entity.Hostel;
import edu.nju.hostelWorld.service.HostelService;
import edu.nju.hostelWorld.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

            map.put("success", true);
            map.put("host_id",id);

        }
        return map;


    }
}

package edu.nju.hostelWorld.service.impl;

import edu.nju.hostelWorld.controller.ManagerController;
import edu.nju.hostelWorld.dao.ApplyDAO;
import edu.nju.hostelWorld.dao.HostelDAO;
import edu.nju.hostelWorld.entity.Apply;
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


}

package edu.nju.hostelWorld.service.impl;

import edu.nju.hostelWorld.dao.HostelDAO;
import edu.nju.hostelWorld.dao.PlanDAO;
import edu.nju.hostelWorld.entity.Hostel;
import edu.nju.hostelWorld.entity.Plan;
import edu.nju.hostelWorld.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 张文玘 on 2017/3/26.
 */
@Service("planService")
public class PlanServiceImpl implements PlanService{
    @Autowired
    PlanDAO planDAO;
    @Autowired
    HostelDAO hostelDAO;
    public List<Plan> getAllPlans(int hostelId) {
        Hostel hostel = hostelDAO.findOne(hostelId);
        return planDAO.findByHostelByHostelId(hostel);
    }

    public boolean addPlan(Plan plan) {
        boolean success = false;
        Plan saved = planDAO.save(plan);
        if(saved!=null){
            success = true;
        }
        return success;
    }

    public void deletePlan(int id) {
        planDAO.delete(id);
    }


}

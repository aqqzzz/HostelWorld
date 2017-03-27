package edu.nju.hostelWorld.service.impl;

import edu.nju.hostelWorld.dao.HostelDAO;
import edu.nju.hostelWorld.dao.PlanDAO;
import edu.nju.hostelWorld.entity.Hostel;
import edu.nju.hostelWorld.entity.Plan;
import edu.nju.hostelWorld.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Map<String, Object> addPlan(Plan plan) {
        Map<String,Object> map = new HashMap<String, Object>();
        if(plan.getStartTime().before(Calendar.getInstance().getTime())){
            map.put("success", false);
            map.put("error", "expired");//计划开始时间早于今天的日期
        }else{
            int planDays = (int)((plan.getEndTime().getTime()- plan.getStartTime().getTime())/(1000*60*60*24));
            if(planDays>100){
                map.put("success",false);
                map.put("error","days");//计划日期过长
            }else{
                Plan returnedPlan = planDAO.save(plan);
                planDAO.flush();
                map.put("success",true);

            }

        }
        return map;
    }

    public Map<String, Object> deletePlan(int id) {
        Map<String, Object> map = new HashMap<String, Object>();
        Plan plan = planDAO.getOne(id);
        if(plan.getStartTime().before(Calendar.getInstance().getTime())){
            map.put("success", false);
            map.put("error", "expired"); //计划开始时间早于今天的日期，不能删除
        }else{
            planDAO.delete(id);
            map.put("success",true);
        }
        return map;

    }

    public List<Plan> getPlanByTime(Date startTime, Date endTime, int hostelId) {
        List<Plan> start = planDAO.findByStartTimeBefore(startTime);
        List<Plan> end = planDAO.findByEndTimeAfter(endTime);

        List<Plan> result = new ArrayList<Plan>();

        for(int i = 0; i < start.size(); i++){
            Plan tmp = start.get(i);
            if(tmp.getHostelByHostelId().getId()!=hostelId){
                continue;
            }
            if(end.contains(tmp)){
                result.add(tmp);
            }
        }
        return result;
    }

    public Plan getPlan(int id){
        Plan plan = planDAO.getOne(id);
        return plan;
    }

}

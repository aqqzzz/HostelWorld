package edu.nju.hostelWorld.service;

import edu.nju.hostelWorld.entity.Plan;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 张文玘 on 2017/3/26.
 */
public interface PlanService {

    public List<Plan> getAllPlans(int hostelId);

    public Map<String, Object> addPlan(Plan plan);

    public Map<String, Object> deletePlan(int id);

    public List<Plan> getPlanByTime(Date startTime, Date endTime, int hostelId);

    public Plan getPlan(int id);
}

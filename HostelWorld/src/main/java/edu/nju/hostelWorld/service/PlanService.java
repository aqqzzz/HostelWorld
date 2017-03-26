package edu.nju.hostelWorld.service;

import edu.nju.hostelWorld.entity.Plan;

import java.util.List;

/**
 * Created by 张文玘 on 2017/3/26.
 */
public interface PlanService {

    public List<Plan> getAllPlans(int hostelId);

    public boolean addPlan(Plan plan);

    public void deletePlan(int id);
}

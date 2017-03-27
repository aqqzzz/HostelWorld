package edu.nju.hostelWorld.service;

import edu.nju.hostelWorld.entity.Apply;
import edu.nju.hostelWorld.entity.Settlement;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 张文玘 on 2017/3/22.
 */
public interface ManagerService {

    public List<Apply> getAllApplyList();

    public Map<String, Object> acceptApply(int id);

    public void rejectApply(int id);

    public List<Settlement> getAllSettlementList();

    public Map<String, Object> acceptSettlement(int id, double rate);

    public List<Settlement> getSettlementListByStatus(byte status);

}

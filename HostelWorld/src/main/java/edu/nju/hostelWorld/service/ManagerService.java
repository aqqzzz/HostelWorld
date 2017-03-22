package edu.nju.hostelWorld.service;

import edu.nju.hostelWorld.entity.Apply;

import java.util.List;
import java.util.Map;

/**
 * Created by 张文玘 on 2017/3/22.
 */
public interface ManagerService {

    public List<Apply> getAllApplyList();

    public Map<String, Object> acceptApply(int id);

    public void rejectApply(int id);
}

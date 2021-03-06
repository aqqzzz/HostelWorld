package edu.nju.hostelWorld.service;

import edu.nju.hostelWorld.entity.Hostel;
import edu.nju.hostelWorld.entity.Plan;

import java.util.List;
import java.util.Map;

/**
 * Created by 张文玘 on 2017/3/20.
 */
public interface HostelService {

    public Map<String, Object> register(Hostel hostel);

    public Map<String, Object> login(int id, String password);

    public Map<String, Object> getHostelInfo(int id);

    public Map<String,Object> editHostelInfo(Hostel hostel);

    public List<Hostel> getAllHostel();

}

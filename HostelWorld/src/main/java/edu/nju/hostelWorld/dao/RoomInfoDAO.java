package edu.nju.hostelWorld.dao;

import edu.nju.hostelWorld.entity.Hostel;
import edu.nju.hostelWorld.entity.Plan;
import edu.nju.hostelWorld.entity.RoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 张文玘 on 2017/3/27.
 */
@Repository
public interface RoomInfoDAO extends JpaRepository<RoomInfo, Integer>{

    List<RoomInfo> findByPlanByPlanId(Plan plan);

    List<RoomInfo> findByHostelByHostelId(Hostel hostel);

    @Modifying
    @Transactional
    @Query("update RoomInfo as r set r.freeTime=?1 where r.id=?2")
    int updateByFreeTime(String freeTime, int id);
}

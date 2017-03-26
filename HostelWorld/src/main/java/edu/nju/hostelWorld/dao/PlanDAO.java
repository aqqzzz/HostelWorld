package edu.nju.hostelWorld.dao;

import edu.nju.hostelWorld.entity.Hostel;
import edu.nju.hostelWorld.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 张文玘 on 2017/3/26.
 */
@Repository
public interface PlanDAO extends JpaRepository<Plan, Integer> {

    List<Plan> findByHostelByHostelId(Hostel hostel);

}

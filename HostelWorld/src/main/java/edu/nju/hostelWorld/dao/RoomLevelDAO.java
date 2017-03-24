package edu.nju.hostelWorld.dao;

import edu.nju.hostelWorld.entity.Hostel;
import edu.nju.hostelWorld.entity.RoomLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 张文玘 on 2017/3/24.
 */
@Repository
public interface RoomLevelDAO extends JpaRepository<RoomLevel, Integer> {

    List<RoomLevel> findByHostelByHostelId(Hostel hostel);

}

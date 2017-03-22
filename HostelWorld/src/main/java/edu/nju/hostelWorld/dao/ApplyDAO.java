package edu.nju.hostelWorld.dao;

import edu.nju.hostelWorld.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 张文玘 on 2017/3/22.
 */
@Repository
public interface ApplyDAO extends JpaRepository<Apply, Integer> {


    @Modifying
    @Transactional
    @Query("update Apply as a set a.status=?1 where a.id=?2")
    int updateApplyStatus(Byte status, int id);

}

package edu.nju.hostelWorld.dao;

import edu.nju.hostelWorld.entity.CustStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by 张文玘 on 2017/3/20.
 */
@Repository
public interface CustStatusDAO extends JpaRepository<CustStatus, Integer>{

    @Modifying
    @Transactional
    @Query("update CustStatus as c set stopTime=?1 where c.id=?2")
    int updateStoptime(Date stopTime, int id);
}

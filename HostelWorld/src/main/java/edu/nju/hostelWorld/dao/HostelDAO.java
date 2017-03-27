package edu.nju.hostelWorld.dao;

import edu.nju.hostelWorld.entity.Hostel;
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
public interface HostelDAO extends JpaRepository<Hostel,Integer>{

    @Modifying
    @Transactional
    @Query("update Hostel as h set h.createTime=?1 where h.id=?2")
    int updateHostelCreateTime(Date date, int id);

    @Modifying
    @Transactional
    @Query("update Hostel as h set h.balance=?1 where h.id=?2")
    int updateByBalance(double balance, int id);

}

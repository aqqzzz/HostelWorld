package edu.nju.hostelWorld.dao;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.JavaMethod;
import edu.nju.hostelWorld.entity.CheckInCust;
import edu.nju.hostelWorld.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by 张文玘 on 2017/3/28.
 */
@Repository
public interface CheckInCustDAO extends JpaRepository<CheckInCust, Integer>{

    @Modifying
    @Transactional
    @Query("update CheckInCust as c set c.leaveTime=?1 where c.id=?2")
    int updateLeaveTime(Date leaveTime, int id);

    List<CheckInCust> findByReserveId(Reserve reserve);

}

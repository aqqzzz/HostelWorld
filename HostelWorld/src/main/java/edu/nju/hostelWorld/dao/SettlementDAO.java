package edu.nju.hostelWorld.dao;

import edu.nju.hostelWorld.entity.Reserve;
import edu.nju.hostelWorld.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by 张文玘 on 2017/3/27.
 */
@Repository
public interface SettlementDAO extends JpaRepository<Settlement, Integer>{

    List<Settlement> findByStatus(byte status);

    @Modifying
    @Transactional
    @Query("update Settlement as s set s.actualRate=?1 where s.id=?2")
    int updateByActualRate(double rate, int id);

    @Modifying
    @Transactional
    @Query("update Settlement as s set s.status=?1 where s.id=?2")
    int updateByStatus(byte status, int id);

    List<Settlement> findByReason(byte reason);

    Settlement findByReserveByReserveId(Reserve reserve);

    @Modifying
    @Transactional
    @Query("update Settlement as s set s.settleTime=?1 where s.id=?2")
    int updateBySettleTime(Date settleTime, int id);
}

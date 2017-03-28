package edu.nju.hostelWorld.dao;

import edu.nju.hostelWorld.entity.Customer;
import edu.nju.hostelWorld.entity.Reserve;
import edu.nju.hostelWorld.entity.RoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface ReserveDAO extends JpaRepository<Reserve, Integer>{

    @Modifying
    @Transactional
    @Query("update Reserve as r set r.status=?1 where r.id=?2")
    int updateReserveStatus(Byte status, int id);

    @Modifying
    @Transactional
    @Query("update Reserve as r set r.actualCheckinTime=?1 where r.id=?2")
    int updateReserveActualCheckInTime(Date date, int id);

    @Modifying
    @Transactional
    @Query("update Reserve as r set r.actualLeaveTime=?1 where r.id=?2")
    int updateByActualLeaveTime(Date date, int id);

    @Modifying
    @Transactional
    @Query("update Reserve as r set r.cancelTime=?1 where r.id=?2")
    int updateByCancelTime(Date date, int id);

    List<Reserve> findByCustomerByCustId(Customer customer);

    List<Reserve> findByRoomInfoById(RoomInfo roomInfo);

    List<Reserve> findByCustomerByCustIdAndStatus(Customer customer, byte status);

    List<Reserve> findByRoomInfoByIdAndStatus(RoomInfo roomInfo, byte status);
}

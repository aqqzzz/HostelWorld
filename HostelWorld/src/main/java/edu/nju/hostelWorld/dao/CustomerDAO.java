package edu.nju.hostelWorld.dao;

import edu.nju.hostelWorld.entity.BankAccount;
import edu.nju.hostelWorld.entity.CustLevel;
import edu.nju.hostelWorld.entity.CustStatus;
import edu.nju.hostelWorld.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 张文玘 on 2017/3/4.
 */
@Repository
public interface CustomerDAO extends JpaRepository<Customer, Long> {

    Customer findByPhone(String phone);

    Customer findByUserid(int id);

    @Modifying
    @Transactional
    @Query("update Customer as c set c.name = ?1 where c.userid=?2")
    int updateNameById(String name, int id);

    @Modifying
    @Transactional
    @Query("update Customer as c set c.gender=?1 where c.userid=?2")
    int updateGenderById(Byte gender, int id);

    @Modifying
    @Transactional
    @Query("update Customer as c set c.bankAccountByBankCard=?1 where c.userid=?2")
    int updateBankAccountById(BankAccount bankAccount, int id);

    @Modifying
    @Transactional
    @Query("update Customer as c set c.balance=?1 where c.userid=?2")
    int updateBalance(double balance, int id);

    @Modifying
    @Transactional
    @Query("update Customer as c set c.status=?1 where c.userid=?2")
    int updateStatus(Byte status, int id);

    @Modifying
    @Transactional
    @Query("update Customer as c set c.point=?1 where c.userid=?2")
    int updatePoints(int point, int id);

    @Modifying
    @Transactional
    @Query("update Customer as c set c.custStatusByUserid=?1 where c.userid=?2")
    int updateCustStatus(CustStatus custStatus, int id);

    @Modifying
    @Transactional
    @Query("update Customer as c set c.custLevelById=?1 where c.userid=?2")
    int updateCustLevelById(CustLevel custLevel, int id);

    @Modifying
    @Transactional
    @Query("update Customer as c set c.consumpTotal=?1 where c.userid=?2")
    int updateConsumpTotal(double consump, int userid);
}

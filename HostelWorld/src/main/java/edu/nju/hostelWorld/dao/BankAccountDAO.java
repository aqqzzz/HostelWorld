package edu.nju.hostelWorld.dao;

import edu.nju.hostelWorld.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 张文玘 on 2017/3/18.
 */
@Repository
public interface BankAccountDAO extends JpaRepository<BankAccount, String>
{
    BankAccount findById(String id);
}

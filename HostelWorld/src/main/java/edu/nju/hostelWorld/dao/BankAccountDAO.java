package edu.nju.hostelWorld.dao;

import edu.nju.hostelWorld.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 张文玘 on 2017/3/18.
 */
public interface BankAccountDAO extends JpaRepository<BankAccount, String>
{
    BankAccount findById(String id);
}

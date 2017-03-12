package edu.nju.hostelWorld.dao;

import edu.nju.hostelWorld.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 张文玘 on 2017/3/4.
 */
@Repository
public interface CustomerDAO extends JpaRepository<Customer, Long> {

    Customer findByPhone(String phone);

    Customer findByUserid(int id);

}

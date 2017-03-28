package edu.nju.hostelWorld.dao;

import edu.nju.hostelWorld.entity.CustLevel;
import edu.nju.hostelWorld.entity.Customer;
import edu.nju.hostelWorld.entity.Hostel;
import edu.nju.hostelWorld.entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 张文玘 on 2017/3/28.
 */
@Repository
public interface PayDAO  extends JpaRepository<Pay, Integer>{

    List<Pay> findByCustomerByCustId(Customer customer);

    List<Pay> findByHostelByHostId(Hostel hostel);
}

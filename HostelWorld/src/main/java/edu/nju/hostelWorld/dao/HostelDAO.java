package edu.nju.hostelWorld.dao;

import edu.nju.hostelWorld.entity.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 张文玘 on 2017/3/20.
 */
@Repository
public interface HostelDAO extends JpaRepository<Hostel,Integer>{

}

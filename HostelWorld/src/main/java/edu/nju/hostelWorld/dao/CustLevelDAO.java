package edu.nju.hostelWorld.dao;

import edu.nju.hostelWorld.entity.CustLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 张文玘 on 2017/3/19.
 */
@Repository
public interface CustLevelDAO extends JpaRepository<CustLevel, Integer>{
    List<CustLevel> findAll();
}

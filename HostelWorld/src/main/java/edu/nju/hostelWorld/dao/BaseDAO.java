package edu.nju.hostelWorld.dao;

import org.hibernate.Session;

/**
 * Created by 张文玘 on 2017/3/3.
 */
public interface BaseDAO {
    public Session getSession();
}

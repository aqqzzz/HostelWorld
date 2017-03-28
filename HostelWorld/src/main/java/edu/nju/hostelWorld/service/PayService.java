package edu.nju.hostelWorld.service;

import edu.nju.hostelWorld.entity.Pay;

import java.util.List;

/**
 * Created by 张文玘 on 2017/3/28.
 */
public interface PayService {
    public List<Pay> getAllPay();

    public List<Pay> getPayByCust(int custId);

    public List<Pay> getPayByHost(int hostId);
}

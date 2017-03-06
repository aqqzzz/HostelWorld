package edu.nju.hostelWorld.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by 张文玘 on 2017/3/4.
 */
@Entity
public class Pay {
    private int id;
    private Double money;
    private Date time;
    private Customer customerByCustId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "money", nullable = true, precision = 0)
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pay pay = (Pay) o;

        if (id != pay.id) return false;
        if (money != null ? !money.equals(pay.money) : pay.money != null) return false;
        if (time != null ? !time.equals(pay.time) : pay.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "cust_id", referencedColumnName = "userid")
    public Customer getCustomerByCustId() {
        return customerByCustId;
    }

    public void setCustomerByCustId(Customer customerByCustId) {
        this.customerByCustId = customerByCustId;
    }
}

package edu.nju.hostelWorld.entity;

import javax.persistence.*;

/**
 * Created by 张文玘 on 2017/3/10.
 */
@Entity
@Table(name = "cust_level", schema = "hostelworld")
public class CustLevel {
    private int id;
    private Double consumpTotal;
    private Integer level;
    private Double discount;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "consump_total", nullable = true, precision = 0)
    public Double getConsumpTotal() {
        return consumpTotal;
    }

    public void setConsumpTotal(Double consumpTotal) {
        this.consumpTotal = consumpTotal;
    }

    @Basic
    @Column(name = "level", nullable = true)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "discount", nullable = true, precision = 0)
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustLevel custLevel = (CustLevel) o;

        if (id != custLevel.id) return false;
        if (consumpTotal != null ? !consumpTotal.equals(custLevel.consumpTotal) : custLevel.consumpTotal != null)
            return false;
        if (level != null ? !level.equals(custLevel.level) : custLevel.level != null) return false;
        if (discount != null ? !discount.equals(custLevel.discount) : custLevel.discount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (consumpTotal != null ? consumpTotal.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        return result;
    }
}

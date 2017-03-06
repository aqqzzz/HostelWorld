package edu.nju.hostelWorld.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by 张文玘 on 2017/3/4.
 */
@Entity
public class Reserve {
    private int id;
    private Date createTime;
    private Double discount;
    private Double original;
    private Double actual;
    private Integer points;
    private Byte status;
    private Date checkinTime;
    private Date leaveTime;
    private Date actualCheckinTime;
    private Date actualLeaveTime;
    private Byte payType;
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
    @Column(name = "create_time", nullable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "discount", nullable = true, precision = 0)
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "original", nullable = true, precision = 0)
    public Double getOriginal() {
        return original;
    }

    public void setOriginal(Double original) {
        this.original = original;
    }

    @Basic
    @Column(name = "actual", nullable = true, precision = 0)
    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }

    @Basic
    @Column(name = "points", nullable = true)
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "checkin_time", nullable = true)
    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    @Basic
    @Column(name = "leave_time", nullable = true)
    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    @Basic
    @Column(name = "actual_checkin_time", nullable = true)
    public Date getActualCheckinTime() {
        return actualCheckinTime;
    }

    public void setActualCheckinTime(Date actualCheckinTime) {
        this.actualCheckinTime = actualCheckinTime;
    }

    @Basic
    @Column(name = "actual_leave_time", nullable = true)
    public Date getActualLeaveTime() {
        return actualLeaveTime;
    }

    public void setActualLeaveTime(Date actualLeaveTime) {
        this.actualLeaveTime = actualLeaveTime;
    }

    @Basic
    @Column(name = "pay_type", nullable = true)
    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reserve reserve = (Reserve) o;

        if (id != reserve.id) return false;
        if (createTime != null ? !createTime.equals(reserve.createTime) : reserve.createTime != null) return false;
        if (discount != null ? !discount.equals(reserve.discount) : reserve.discount != null) return false;
        if (original != null ? !original.equals(reserve.original) : reserve.original != null) return false;
        if (actual != null ? !actual.equals(reserve.actual) : reserve.actual != null) return false;
        if (points != null ? !points.equals(reserve.points) : reserve.points != null) return false;
        if (status != null ? !status.equals(reserve.status) : reserve.status != null) return false;
        if (checkinTime != null ? !checkinTime.equals(reserve.checkinTime) : reserve.checkinTime != null) return false;
        if (leaveTime != null ? !leaveTime.equals(reserve.leaveTime) : reserve.leaveTime != null) return false;
        if (actualCheckinTime != null ? !actualCheckinTime.equals(reserve.actualCheckinTime) : reserve.actualCheckinTime != null)
            return false;
        if (actualLeaveTime != null ? !actualLeaveTime.equals(reserve.actualLeaveTime) : reserve.actualLeaveTime != null)
            return false;
        if (payType != null ? !payType.equals(reserve.payType) : reserve.payType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (original != null ? original.hashCode() : 0);
        result = 31 * result + (actual != null ? actual.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (checkinTime != null ? checkinTime.hashCode() : 0);
        result = 31 * result + (leaveTime != null ? leaveTime.hashCode() : 0);
        result = 31 * result + (actualCheckinTime != null ? actualCheckinTime.hashCode() : 0);
        result = 31 * result + (actualLeaveTime != null ? actualLeaveTime.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "cust_id", referencedColumnName = "userid", nullable = false)
    public Customer getCustomerByCustId() {
        return customerByCustId;
    }

    public void setCustomerByCustId(Customer customerByCustId) {
        this.customerByCustId = customerByCustId;
    }
}

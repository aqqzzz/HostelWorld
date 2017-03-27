package edu.nju.hostelWorld.entity;

import javax.persistence.*;

/**
 * Created by 张文玘 on 2017/3/27.
 */
@Entity
public class Settlement {
    private int id;
    private Byte reason;
    private Byte status;
    private Double amount;
    private Double actualRate;
    private Reserve reserveByReserveId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "reason", nullable = true)
    public Byte getReason() {
        return reason;
    }

    public void setReason(Byte reason) {
        this.reason = reason;
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
    @Column(name = "amount", nullable = true, precision = 0)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "actual_rate")
    public Double getActualRate(){ return actualRate; }

    public void setActualRate(Double actualRate) {
        this.actualRate = actualRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Settlement that = (Settlement) o;

        if (id != that.id) return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "reserve_id", referencedColumnName = "id")
    public Reserve getReserveByReserveId(){ return reserveByReserveId; }

    public void setReserveByReserveId(Reserve reserveByReserveId) {
        this.reserveByReserveId = reserveByReserveId;
    }
}

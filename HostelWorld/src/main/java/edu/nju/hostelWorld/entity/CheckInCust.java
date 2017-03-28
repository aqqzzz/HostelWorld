package edu.nju.hostelWorld.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 张文玘 on 2017/3/28.
 */
@Entity
@Table(name = "check_in_cust", schema = "hostelworld", catalog = "")
public class CheckInCust {
    private int id;
    private Reserve reserveId;
    private Date arriveTime;
    private Date leaveTime;
    private Byte type;
    private String custName;
    private String custPhone;
    private RoomInfo roomInfoId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "arrive_time", nullable = true)
    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
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
    @Column(name = "type", nullable = true)
    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Basic
    @Column(name = "cust_name", nullable = true, length = 255)
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    @Basic
    @Column(name = "cust_phone", nullable = true, length = 255)
    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckInCust that = (CheckInCust) o;

        if (id != that.id) return false;
        if (reserveId != null ? !reserveId.equals(that.reserveId) : that.reserveId != null) return false;
        if (arriveTime != null ? !arriveTime.equals(that.arriveTime) : that.arriveTime != null) return false;
        if (leaveTime != null ? !leaveTime.equals(that.leaveTime) : that.leaveTime != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (custName != null ? !custName.equals(that.custName) : that.custName != null) return false;
        if (custPhone != null ? !custPhone.equals(that.custPhone) : that.custPhone != null) return false;
        if (roomInfoId != null ? !roomInfoId.equals(that.roomInfoId) : that.roomInfoId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (reserveId != null ? reserveId.hashCode() : 0);
        result = 31 * result + (arriveTime != null ? arriveTime.hashCode() : 0);
        result = 31 * result + (leaveTime != null ? leaveTime.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (custName != null ? custName.hashCode() : 0);
        result = 31 * result + (custPhone != null ? custPhone.hashCode() : 0);
        result = 31 * result + (roomInfoId != null ? roomInfoId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "reserve_id" ,referencedColumnName = "id")
    public Reserve getReserveId(){ return  reserveId; }

    public void setReserveId(Reserve reserveId) {
        this.reserveId = reserveId;
    }

    @ManyToOne
    @JoinColumn(name = "room_info_id", referencedColumnName = "id")
    public RoomInfo getRoomInfoId(){ return roomInfoId; }

    public void setRoomInfoId(RoomInfo roomInfoId) {
        this.roomInfoId = roomInfoId;
    }
}

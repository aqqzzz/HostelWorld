package edu.nju.hostelWorld.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by 张文玘 on 2017/3/10.
 */
@Entity
@Table(name = "room_info", schema = "hostelworld", catalog = "")
public class RoomInfo {
    private int id;
    private Byte roomType;
    private int roomNum;
    private String freeTime;
    private Date today;
    private Hostel hostelByHostelId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "room_type", nullable = true)
    public Byte getRoomType() {
        return roomType;
    }

    public void setRoomType(Byte roomType) {
        this.roomType = roomType;
    }

    @Basic
    @Column(name = "room_num", nullable = false)
    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    @Basic
    @Column(name = "free_time", nullable = true, length = 0)
    public String getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(String freeTime) {
        this.freeTime = freeTime;
    }

    @Basic
    @Column(name = "today", nullable = true)
    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomInfo roomInfo = (RoomInfo) o;

        if (id != roomInfo.id) return false;
        if (roomNum != roomInfo.roomNum) return false;
        if (roomType != null ? !roomType.equals(roomInfo.roomType) : roomInfo.roomType != null) return false;
        if (freeTime != null ? !freeTime.equals(roomInfo.freeTime) : roomInfo.freeTime != null) return false;
        if (today != null ? !today.equals(roomInfo.today) : roomInfo.today != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        result = 31 * result + roomNum;
        result = 31 * result + (freeTime != null ? freeTime.hashCode() : 0);
        result = 31 * result + (today != null ? today.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "hostel_id", referencedColumnName = "id", nullable = false)
    public Hostel getHostelByHostelId() {
        return hostelByHostelId;
    }

    public void setHostelByHostelId(Hostel hostelByHostelId) {
        this.hostelByHostelId = hostelByHostelId;
    }
}

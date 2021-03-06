package edu.nju.hostelWorld.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by 张文玘 on 2017/3/10.
 */
@Entity
@Table(name = "room_level", schema = "hostelworld")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomLevel {
    private int id;
    private Byte roomType;
    private Integer roomCount;
    private String roomName;
    private int maxPeople;
    private int startRoomNum;
    private int endRoomNum;
    private String discription;
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
    @Column(name = "room_count", nullable = true)
    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    @Basic
    @Column(name = "room_name", nullable = true)
    public String getRoomName(){return roomName;}

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Basic
    @Column(name="max_people", nullable = true)
    public int getMaxPeople(){return maxPeople;}

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    @Basic
    @Column(name = "start_room_num", nullable = true)
    public int getStartRoomNum(){ return startRoomNum; }

    public void setStartRoomNum(int startRoomNum) {
        this.startRoomNum = startRoomNum;
    }

    @Basic
    @Column(name = "end_room_num", nullable = true)
    public int getEndRoomNum(){ return endRoomNum; }

    public void setEndRoomNum(int endRoomNum) {
        this.endRoomNum = endRoomNum;
    }

    @Basic
    @Column(name = "discription", nullable = true, length = 0)
    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomLevel roomLevel = (RoomLevel) o;

        if (id != roomLevel.id) return false;
        if (roomType != null ? !roomType.equals(roomLevel.roomType) : roomLevel.roomType != null) return false;
        if (roomCount != null ? !roomCount.equals(roomLevel.roomCount) : roomLevel.roomCount != null) return false;
        if (discription != null ? !discription.equals(roomLevel.discription) : roomLevel.discription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        result = 31 * result + (roomCount != null ? roomCount.hashCode() : 0);
        result = 31 * result + (discription != null ? discription.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "hostel_id", referencedColumnName = "id")
    public Hostel getHostelByHostelId() {
        return hostelByHostelId;
    }

    public void setHostelByHostelId(Hostel hostelByHostelId) {
        this.hostelByHostelId = hostelByHostelId;
    }
}

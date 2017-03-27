package edu.nju.hostelWorld.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 张文玘 on 2017/3/10.
 */
@Entity
public class Plan {
    private int id;
    private Date startTime;
    private Date endTime;
    private RoomLevel roomLevelById;
    private Double price;
    private int roomLevelId;
    private Hostel hostelByHostelId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "start_time", nullable = true)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time", nullable = true)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Transient
    public int getRoomLevelId(){ return roomLevelId; }

    public void setRoomLevelId(int roomLevelId) {
        this.roomLevelId = roomLevelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plan plan = (Plan) o;

        if (id != plan.id) return false;
        if (startTime != null ? !startTime.equals(plan.startTime) : plan.startTime != null) return false;
        if (endTime != null ? !endTime.equals(plan.endTime) : plan.endTime != null) return false;
        if (roomLevelById != null ? !roomLevelById.equals(plan.roomLevelById) : plan.roomLevelById != null) return false;
        if (price != null ? !price.equals(plan.price) : plan.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (roomLevelById != null ? roomLevelById.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name="room_level_id", referencedColumnName = "id")
    public RoomLevel getRoomLevelById(){return roomLevelById;}

    public void setRoomLevelById(RoomLevel roomLevelById) {
        this.roomLevelById = roomLevelById;
    }
}

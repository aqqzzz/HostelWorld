package edu.nju.hostelWorld.entity;

import javax.persistence.*;

/**
 * Created by 张文玘 on 2017/3/10.
 */
@Entity
@Table(name = "hostel_keeper", schema = "hostelworld")
public class HostelKeeper {
    private int id;
    private String keeperName;
    private String keeperIdCard;
    private String keeperPhone;
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
    @Column(name = "name", nullable = true, length = 0)
    public String getKeeperName() {
        return keeperName;
    }

    public void setKeeperName(String name) {
        this.keeperName = name;
    }

    @Basic
    @Column(name = "id_card", nullable = true, length = 0)
    public String getKeeperIdCard() {
        return keeperIdCard;
    }

    public void setKeeperIdCard(String idCard) {
        this.keeperIdCard = idCard;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 0)
    public String getKeeperPhone() {
        return keeperPhone;
    }

    public void setKeeperPhone(String phone) {
        this.keeperPhone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HostelKeeper that = (HostelKeeper) o;

        if (id != that.id) return false;
        if (keeperName != null ? !keeperName.equals(that.keeperName) : that.keeperName != null) return false;
        if (keeperIdCard != null ? !keeperIdCard.equals(that.keeperIdCard) : that.keeperIdCard != null) return false;
        if (keeperPhone != null ? !keeperPhone.equals(that.keeperPhone) : that.keeperPhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (keeperName != null ? keeperName.hashCode() : 0);
        result = 31 * result + (keeperIdCard != null ? keeperIdCard.hashCode() : 0);
        result = 31 * result + (keeperPhone != null ? keeperPhone.hashCode() : 0);
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

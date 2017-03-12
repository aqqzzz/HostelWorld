package edu.nju.hostelWorld.entity;

import javax.persistence.*;

/**
 * Created by 张文玘 on 2017/3/10.
 */
@Entity
@Table(name = "hostel_keeper", schema = "hostelworld", catalog = "")
public class HostelKeeper {
    private int id;
    private String name;
    private String idCard;
    private String phone;
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "id_card", nullable = true, length = 0)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 0)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HostelKeeper that = (HostelKeeper) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (idCard != null ? !idCard.equals(that.idCard) : that.idCard != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idCard != null ? idCard.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
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

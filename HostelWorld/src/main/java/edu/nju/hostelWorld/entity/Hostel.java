package edu.nju.hostelWorld.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Date;

/**
 * Created by 张文玘 on 2017/3/10.
 */
@Entity
public class Hostel {
    private int id;
    private String hostName;
    private String hostPassword;
    private String discription;
    private String location;
    private Date createTime;
    private String hostTel;
    private BankAccount hostBankAccountByBankCard;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 0)
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String name) {
        this.hostName = name;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 0)
    @NotEmpty(message = "请输入您的密码")
    public String getHostPassword() {
        return hostPassword;
    }

    public void setHostPassword(String password) {
        this.hostPassword = password;
    }

    @Basic
    @Column(name = "discription", nullable = true, length = 0)
    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @Basic
    @Column(name = "location", nullable = true, length = 0)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
    @Column(name = "phone", nullable = true, length = 0)
    public String getHostTel() {
        return hostTel;
    }

    public void setHostTel(String phone) {
        this.hostTel = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hostel hostel = (Hostel) o;

        if (id != hostel.id) return false;
        if (hostName != null ? !hostName.equals(hostel.hostName) : hostel.hostName != null) return false;
        if (hostPassword != null ? !hostPassword.equals(hostel.hostPassword) : hostel.hostPassword != null) return false;
        if (discription != null ? !discription.equals(hostel.discription) : hostel.discription != null) return false;
        if (location != null ? !location.equals(hostel.location) : hostel.location != null) return false;
        if (createTime != null ? !createTime.equals(hostel.createTime) : hostel.createTime != null) return false;
        if (hostTel != null ? !hostTel.equals(hostel.hostTel) : hostel.hostTel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (hostName != null ? hostName.hashCode() : 0);
        result = 31 * result + (hostPassword != null ? hostPassword.hashCode() : 0);
        result = 31 * result + (discription != null ? discription.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (hostTel != null ? hostTel.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "bank_card", referencedColumnName = "id")
    public BankAccount getHostBankAccountByBankCard() {
        return hostBankAccountByBankCard;
    }

    public void setHostBankAccountByBankCard(BankAccount bankAccountByBankCard) {
        this.hostBankAccountByBankCard = bankAccountByBankCard;
    }
}

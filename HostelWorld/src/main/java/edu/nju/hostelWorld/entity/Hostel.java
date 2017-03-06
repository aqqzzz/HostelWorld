package edu.nju.hostelWorld.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by 张文玘 on 2017/3/4.
 */
@Entity
public class Hostel {
    private int id;
    private String name;
    private String password;
    private String discription;
    private String location;
    private Date createTime;
    private String phone;
    private BankAccount bankAccountByBankCard;

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 0)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

        Hostel hostel = (Hostel) o;

        if (id != hostel.id) return false;
        if (name != null ? !name.equals(hostel.name) : hostel.name != null) return false;
        if (password != null ? !password.equals(hostel.password) : hostel.password != null) return false;
        if (discription != null ? !discription.equals(hostel.discription) : hostel.discription != null) return false;
        if (location != null ? !location.equals(hostel.location) : hostel.location != null) return false;
        if (createTime != null ? !createTime.equals(hostel.createTime) : hostel.createTime != null) return false;
        if (phone != null ? !phone.equals(hostel.phone) : hostel.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (discription != null ? discription.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "bank_card", referencedColumnName = "id")
    public BankAccount getBankAccountByBankCard() {
        return bankAccountByBankCard;
    }

    public void setBankAccountByBankCard(BankAccount bankAccountByBankCard) {
        this.bankAccountByBankCard = bankAccountByBankCard;
    }
}

package edu.nju.hostelWorld.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 张文玘 on 2017/3/10.
 */
@Entity
public class Customer {
    private int userid;
    private String password;
    private String phone;
    private Byte status;
    private String name;
    private Date birthday;
    private Byte gender;
    private Double balance;
    private Integer point;
    private Double consumpTotal;
    private CustStatus custStatusByUserid;
    private BankAccount bankAccountByBankCard;

    @Id
    @Column(name = "userid", nullable = false)
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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
    @Column(name = "phone", nullable = true, length = 0)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
    @Column(name = "name", nullable = true, length = 0)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "gender", nullable = true)
    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "balance", nullable = true, precision = 0)
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "point", nullable = true)
    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Basic
    @Column(name = "consump_total", nullable = true, precision = 0)
    public Double getConsumpTotal() {
        return consumpTotal;
    }

    public void setConsumpTotal(Double consumpTotal) {
        this.consumpTotal = consumpTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (userid != customer.userid) return false;
        if (password != null ? !password.equals(customer.password) : customer.password != null) return false;
        if (phone != null ? !phone.equals(customer.phone) : customer.phone != null) return false;
        if (status != null ? !status.equals(customer.status) : customer.status != null) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (birthday != null ? !birthday.equals(customer.birthday) : customer.birthday != null) return false;
        if (gender != null ? !gender.equals(customer.gender) : customer.gender != null) return false;
        if (balance != null ? !balance.equals(customer.balance) : customer.balance != null) return false;
        if (point != null ? !point.equals(customer.point) : customer.point != null) return false;
        if (consumpTotal != null ? !consumpTotal.equals(customer.consumpTotal) : customer.consumpTotal != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (point != null ? point.hashCode() : 0);
        result = 31 * result + (consumpTotal != null ? consumpTotal.hashCode() : 0);
        return result;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    public CustStatus getCustStatusByUserid() {
        return custStatusByUserid;
    }

    public void setCustStatusByUserid(CustStatus custStatusByUserid) {
        this.custStatusByUserid = custStatusByUserid;
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

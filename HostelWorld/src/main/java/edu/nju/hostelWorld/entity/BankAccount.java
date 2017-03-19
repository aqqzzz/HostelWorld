package edu.nju.hostelWorld.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * Created by 张文玘 on 2017/3/10.
 */
@Entity
@Table(name = "bank_account", schema = "hostelworld")
public class BankAccount {
    private String id;
    private Double balance;

    @Id
    @Column(name = "id", nullable = false, length = 20)
    @Length(min=16, max=19,message = "请输入16~19位的银行卡号！")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "balance", nullable = true, precision = 0)
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccount that = (BankAccount) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }
}

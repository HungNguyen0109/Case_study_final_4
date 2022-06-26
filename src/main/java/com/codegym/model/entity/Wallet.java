package com.codegym.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wallets", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Icon icon;

    private String name;

    private int total;

    private int balance;

    private Date date;

    private String note;

    @ManyToOne
    private MoneyType moneyType;

    @ManyToOne
    private User user;

    public Wallet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public MoneyType getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(MoneyType moneyType) {
        this.moneyType = moneyType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Wallet(Long id, String name, Date date, Icon icon, MoneyType moneyType, int total, int balance, String note, User user) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.total = total;
        this.balance = balance;
        this.date = date;
        this.note = note;
        this.moneyType = moneyType;
        this.user = user;
    }

    public Wallet(Icon icon, String name, int total, int balance, Date date, String note, MoneyType moneyType, User user) {
        this.icon = icon;
        this.name = name;
        this.total = total;
        this.balance = balance;
        this.date = date;
        this.note = note;
        this.moneyType = moneyType;
        this.user = user;
    }

    public Wallet(String name, Icon icon,  int total, MoneyType moneyType, String note,  User user) {
        this.icon = icon;
        this.name = name;
        this.total = total;
        this.note = note;
        this.moneyType = moneyType;
        this.user = user;
    }
    public Wallet(String name,  int total, MoneyType moneyType, String note,  User user) {
        this.name = name;
        this.total = total;
        this.note = note;
        this.moneyType = moneyType;
        this.user = user;
    }


}

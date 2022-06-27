package com.codegym.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class AddMoney {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int money;

    private Date date;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Wallet wallet;

    @ManyToOne(cascade = {CascadeType.ALL})
    private User user;

    public AddMoney() {
    }


    public AddMoney(int money, Date date, Wallet wallet) {
        this.money = money;
        this.date = date;
        this.wallet = wallet;
    }

    public AddMoney(int money) {
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

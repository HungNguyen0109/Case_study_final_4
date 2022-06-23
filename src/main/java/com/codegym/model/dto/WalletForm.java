package com.codegym.model.dto;


import com.codegym.model.entity.MoneyType;
import com.codegym.model.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class WalletForm {
    private Long id;

    private MultipartFile icon;

    private String name;

    private int total;

    private int balance;

    private Date date;

    private String note;

    private MoneyType moneyType;

    private User user;

    public WalletForm() {
    }
    public WalletForm(MultipartFile icon, String name, int total , MoneyType moneyType, int balance, String note, User user) {
        this.icon = icon;
        this.name = name;
        this.total = total;
        this.balance = balance;
        this.note = note;
        this.moneyType = moneyType;
        this.user = user;
    }


    public WalletForm(Long id, MultipartFile icon, String name, int total, int balance, Date date, String note, MoneyType moneyType, User user) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile getIcon() {
        return icon;
    }

    public void setIcon(MultipartFile icon) {
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
}

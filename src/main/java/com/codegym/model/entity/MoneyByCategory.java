package com.codegym.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;




@Entity
@Table(name = "money_category")
@Data
public class MoneyByCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int sumMoney;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    public MoneyByCategory() {
    }

    public MoneyByCategory( int sumMoney, Category category, User user) {
        this.sumMoney = sumMoney;
        this.category = category;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(int sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package com.codegym.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserFinancial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int income;

    private int outcome;

    private int balance;

    private int totalWallet;

    public UserFinancial() {
    }

    public UserFinancial(int income, int outcome, int balance, int totalWallet) {
        this.income = income;
        this.outcome = outcome;
        this.balance = balance;
        this.totalWallet = totalWallet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getOutcome() {
        return outcome;
    }

    public void setOutcome(int outcome) {
        this.outcome = outcome;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getTotalWallet() {
        return totalWallet;
    }

    public void setTotalWallet(int totalƯallet) {
        this.totalWallet = totalƯallet;
    }
}

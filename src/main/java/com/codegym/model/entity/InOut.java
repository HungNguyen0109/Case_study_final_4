package com.codegym.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class InOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int day;

    private int year;

    private int month;

    private int inFlow;

    private int outFlow;

    @ManyToOne
    private Wallet wallet;

    @ManyToOne
    private User user;


    public InOut() {
    }

    public InOut(int month, int year, int inFlow, int outFlow, User user) {
        this.day = day;
        this.year = year;
        this.month = month;
        this.inFlow = inFlow;
        this.outFlow = outFlow;
        this.user = user;
    }

    public InOut(int day, int year, int month, int inFlow, int outFlow, Wallet wallet) {
        this.day = day;
        this.year = year;
        this.month = month;
        this.inFlow = inFlow;
        this.outFlow = outFlow;
        this.wallet = wallet;
    }

    public InOut(Long id, int year, int month, int inFlow, int outFlow, Wallet wallet) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.inFlow = inFlow;
        this.outFlow = outFlow;
        this.wallet = wallet;
    }

    public InOut(int year, int month, int inFlow, int outFlow, Wallet wallet) {
        this.year = year;
        this.month = month;
        this.inFlow = inFlow;
        this.outFlow = outFlow;
        this.wallet = wallet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getInFlow() {
        return inFlow;
    }

    public void setInFlow(int inFlow) {
        this.inFlow = inFlow;
    }

    public int getOutFlow() {
        return outFlow;
    }

    public void setOutFlow(int outFlow) {
        this.outFlow = outFlow;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}

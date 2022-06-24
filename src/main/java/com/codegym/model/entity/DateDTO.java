package com.codegym.model.entity;

import java.util.Date;

public class DateDTO {
    Wallet wallet;
    Date date1;
    Date date2;

    public DateDTO(Date date1, Date date2) {
        this.date1 = date1;
        this.date2 = date2;
    }


    public DateDTO(Wallet wallet, Date date1, Date date2) {
        this.wallet = wallet;
        this.date1 = date1;
        this.date2 = date2;
    }

    public DateDTO() {
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}

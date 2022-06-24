package com.codegym.model.entity;

public class YearMonth {
    int Year;

    int Month;

    Wallet wallet;

    public YearMonth(int year, int month, Wallet wallet) {
        Year = year;
        Month = month;
        this.wallet = wallet;
    }

    public YearMonth() {
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}

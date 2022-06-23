package com.codegym.model.transactionInDay;

public interface TransactionInDay {
    Long getId();

    String getCategory();

    int getAmount();

    String getWallet();

    String getNote();
}

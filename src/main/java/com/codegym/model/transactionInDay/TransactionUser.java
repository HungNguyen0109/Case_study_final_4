package com.codegym.model.transactionInDay;

import java.util.Date;

public interface TransactionUser {
    Long getId();

    int getAmount();

    Date getDate();

    String getNote();

    String getCategory();

    String getWallet();
}

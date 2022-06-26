package com.codegym.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO1 {
    private String wallet_name;
    private String category_name;
    private int money_amount;
    private Date date;
    private String note;
}

package com.codegym.model.dto;

import com.codegym.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDTO {
    private int amount;

    private String note;

    private Date date;

    private Category category;

    private String moneyType;
}

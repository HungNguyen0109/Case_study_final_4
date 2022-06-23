package com.codegym.service.Transaction;

import com.codegym.model.entity.Transaction;
import com.codegym.model.transactionInDay.*;
import com.codegym.service.IGeneralService;

import java.util.Date;
import java.util.List;

public interface ITransactionSV extends IGeneralService<Transaction> {

    Iterable<Transaction> getTransactionInDay( Long id);

    Iterable<Transaction> getTransactionInDayByIdWallet(Long id);

    Iterable<SumInDay> getSumInDay(Long id);

    Iterable<Transaction> getAllTransactionByIdWallet(Long id);

    List<Transaction> getListTransactionUser(Long id);


    Iterable<SumInDay> getSumTransactionWallet(Long id);
    Iterable<Transaction> getListTransactionInTime(Date date1, Date date2, Long id);

    Iterable<Transaction> getListTransactionInTimeByIdWallet(Date date1, Date date2, Long idWallet);

    int getInFlow(Long idWallet, int month, int year);

    int getOutFlow(Long idWallet, int month, int year);

}

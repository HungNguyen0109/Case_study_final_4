package com.codegym.service.Transaction;

import com.codegym.model.entity.Transaction;
import com.codegym.model.transactionInDay.SumInDay;
import com.codegym.repository.ITransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class TransactionSV implements ITransactionSV{
    @Autowired
    private ITransactionRepo transactionRepo;

    @Override
    public List<Transaction> findAll() {
        return transactionRepo.findAll();
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    @Override
    public void removeById(Long id) {
        transactionRepo.deleteById(id);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return transactionRepo.findById(id);
    }

    @Override
    public Iterable<Transaction> getTransactionInDay(Long idUser) {
        return transactionRepo.getTransactionInDay(idUser);
    }

    @Override
    public Iterable<Transaction> getTransactionInDayByIdWallet(Long id) {
        return transactionRepo.getTransactionInDayByIdWallet(id);
    }

    @Override
    public Iterable<SumInDay> getSumInDay(Long id) {
        return transactionRepo.getSumInDay(id);
    }

    @Override
    public Iterable<Transaction> getAllTransactionByIdWallet(Long id) {
        return transactionRepo.getAllTransactionByIdWallet(id);
    }

    @Override
    public List<Transaction> getListTransactionUser(Long id) {
        return transactionRepo.getListTransactionUser(id);
    }


    @Override
    public Iterable<SumInDay> getSumTransactionWallet(Long id) {
        return transactionRepo.getSumTransactionWallet(id);
    }

    @Override
    public Iterable<Transaction> getListTransactionInTime(Date date1, Date date2, Long id) {
        return transactionRepo.getListTransactionInTime(date1, date2, id);
    }

    @Override
    public Iterable<Transaction> getListTransactionInTimeByIdWallet(Date date1, Date date2, Long idWallet) {
        return transactionRepo.getListTransactionInTimeByIdWallet(date1, date2, idWallet);
    }

    @Override
    public int getInFlow(Long idWallet, int month, int year) {
        return transactionRepo.getInFlow(idWallet, month, year);
    }

    @Override
    public int getOutFlow(Long idWallet, int month, int year) {
        return transactionRepo.getOutFlow(idWallet, month, year);
    }

}

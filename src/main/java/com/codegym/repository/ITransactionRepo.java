package com.codegym.repository;

import com.codegym.model.entity.MoneyByCategory;
import com.codegym.model.entity.Transaction;
import com.codegym.model.transactionInDay.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ITransactionRepo extends JpaRepository<Transaction, Long> {

    @Query(nativeQuery = true, value = "select * from transactions where date = current_date and user_id = ?;")
    Iterable<Transaction> getTransactionInDay(Long idUser);

    @Query(nativeQuery = true, value ="select * from transactions where date = current_date and wallet_id = ?;")
    Iterable<Transaction> getTransactionInDayByIdWallet(Long id);

    @Query(nativeQuery = true, value = "select sum(amount) as total, mt.name as type from transactions join wallets w on w.id = transactions.wallet_id join money_type mt on mt.id = w.money_type_id where transactions.date = current_date and w.id = ?;\n")
    Iterable<SumInDay> getSumInDay(Long id);

    @Query(nativeQuery = true, value = "select sum(amount) as total, mt.name as type from transactions join wallets w on w.id = transactions.wallet_id join money_type mt on mt.id = w.money_type_id where w.id = ?;\n")
    Iterable<SumInDay> getSumTransactionWallet(Long id);

    @Query(nativeQuery = true, value ="select * from transactions where wallet_id = ?;")
    Iterable<Transaction> getAllTransactionByIdWallet(Long id);

    @Query( nativeQuery = true, value = "select * from transactions where user_id = ?1" )
    List<Transaction> getListTransactionUser(Long id);

    @Query(value = "select money_type_id from wallets where id = ?1", nativeQuery = true)
    Long findMoney(Long id);

    @Query( nativeQuery = true, value = "SELECT * FROM transactions\n" +
            "WHERE date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE) group by id having user_id = ?;" )
    Iterable<Transaction> getListTransactionInTime(Date date1, Date date2, Long id);

    @Query( nativeQuery = true, value = "SELECT * FROM transactions\n" +
            "WHERE date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE) group by id having wallet_id = ?;" )
    Iterable<Transaction> getListTransactionInTimeByIdWallet(Date date1, Date date2, Long idWallet);

    @Query(nativeQuery = true, value = "select sum(money) as inFlow from add_money where wallet_id = ? and Month(date) = ? and Year(date) = ? ;")
    int getInFlow(Long idWallet, int month, int year);

    @Query(nativeQuery = true, value = "select sum(amount) as outFlow from transactions where wallet_id = ? and Month(date) = ? and Year(date) = ?;")
    int getOutFlow(Long idWallet, int month, int year);

    @Query( nativeQuery = true, value = "select * from transactions where user_id = ?;" )
    Iterable<Transaction> getListTransactionsByUser(Long idUser);

}

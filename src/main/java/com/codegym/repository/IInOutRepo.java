package com.codegym.repository;

import com.codegym.model.entity.InOut;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInOutRepo extends CrudRepository<InOut, Long> {
    @Query(nativeQuery = true, value = "select sum(money) as inFlow from add_money where wallet_id = ? and Day(date) = ? and Month(date) = ? and Year(date) = ? ;")
    Integer getInFlow(Long idWallet,int day, int month, int year);

    @Query(nativeQuery = true, value = "select sum(amount) as outFlow from transactions where wallet_id = ? and Day(date) = ? and Month(date) = ? and Year(date) = ?;")
    Integer getOutFlow(Long idWallet,int day, int month, int year);

    @Query(nativeQuery = true, value = "select sum(total) as `income` from wallets where user_id = ?;")
    Integer getIncomeTotal(Long userId);

    @Query(nativeQuery = true, value = "select sum(amount) as `outcome` from transactions where user_id = ?;")
    Integer getOutcomeTotal(Long userId);

    @Query(nativeQuery = true, value = "select sum(balance) as `balance` from wallets where user_id = ?;")
    Integer getBalanceTotal(Long userId);

    @Query(nativeQuery = true, value = "select count(*) from wallets where user_id = ?;")
    Integer getWalletTotal(Long userId);


}

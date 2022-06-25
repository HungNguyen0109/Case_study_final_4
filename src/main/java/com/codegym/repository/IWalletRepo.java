package com.codegym.repository;

import com.codegym.model.dto.SumMoney;
import com.codegym.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public interface IWalletRepo extends JpaRepository<Wallet, Long> {
    @Query(nativeQuery = true, value = "select* from wallets where user_id = ?;")
    List<Wallet> getWalletByUserId(Long userId);

    @Modifying
    @Query(value = "call deleteWallet(?1)", nativeQuery = true)
    void deleteWallet(Long wallet_id);

    @Query(nativeQuery = true, value = "select mt.id, mt.name as moneyType, sum(balance) as balance from wallets as w\n" +
            "join money_type mt on w.money_type_id = mt.id\n" +
            "where user_id = ? group by mt.name;")
    List<SumMoney> getSumMoney(Long user_id);



}

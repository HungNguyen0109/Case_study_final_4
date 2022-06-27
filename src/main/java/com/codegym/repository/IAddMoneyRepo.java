package com.codegym.repository;

import com.codegym.model.entity.AddMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IAddMoneyRepo extends JpaRepository<AddMoney, Long> {
    @Query(nativeQuery = true, value = "select * from add_money where wallet_id = ?;")
    List<AddMoney> getAddMoneyByWallet(Long idWallet);

    @Query( nativeQuery = true, value = "SELECT * FROM add_money WHERE date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE) group by id having wallet_id = ?;\n" )
    Iterable<AddMoney> getListAddMoneyInTimeByIdWallet(Date date1, Date date2, Long idWallet);

    @Query(nativeQuery = true, value = "select * from add_money join wallets w on w.id = add_money.wallet_id where user_id = ?;\n")
    Iterable<AddMoney> getAllAddMoneyByIdUser(Long idUser);

    @Query( nativeQuery = true, value = "select * from add_money where user_id = ?" )
    Iterable<AddMoney> getListAddMoneyByUser(Long idUser);


}

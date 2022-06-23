package com.codegym.repository;

import com.codegym.model.entity.ShareWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IShareWalletRepository extends JpaRepository<ShareWallet, Long> {
    @Modifying
    @Query(value = "select wallet_id from share_wallet where user_id = ?1", nativeQuery = true)
    List<Long> findListWalletShare(Long id);
    @Modifying
    @Query(value = "select user_id from share_wallet where wallet_id = ?1", nativeQuery = true)
    List<Long> findWhoWasShared(Long id);

}

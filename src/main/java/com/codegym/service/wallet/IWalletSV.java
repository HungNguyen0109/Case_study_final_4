package com.codegym.service.wallet;

import com.codegym.model.entity.Wallet;
import com.codegym.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IWalletSV extends IGeneralService<Wallet> {
    List<Wallet> getWalletByUserId(Long userId);
    void deleteWallet(Long wallet_id);

    boolean existsByName(String walletName);

    Optional<Wallet> findWalletByName(String name);


}

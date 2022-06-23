package com.codegym.service.wallet;

import com.codegym.model.entity.Wallet;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IWalletSV extends IGeneralService<Wallet> {
    List<Wallet> getWalletByUserId(Long userId);
    void deleteWallet(Long wallet_id);

}

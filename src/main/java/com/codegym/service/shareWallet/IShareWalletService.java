package com.codegym.service.shareWallet;

import com.codegym.model.entity.ShareWallet;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IShareWalletService extends IGeneralService<ShareWallet> {
    List<Long> findListWalletShare(Long id);

    List<Long> findWhoWasShared(Long id);

}

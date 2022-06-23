package com.codegym.service.shareWallet;

import com.codegym.model.entity.ShareWallet;
import com.codegym.repository.IShareWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ShareWalletService implements IShareWalletService{
    @Autowired
    private IShareWalletRepository shareWalletRepository;
    @Override
    public List<ShareWallet> findAll() {
        return shareWalletRepository.findAll();
    }

    @Override
    public ShareWallet save(ShareWallet shareWallet) {
        return shareWalletRepository.save(shareWallet);
    }

    @Override
    public void removeById(Long id) {
        shareWalletRepository.deleteById(id);
    }

    @Override
    public Optional<ShareWallet> findById(Long id) {
        return shareWalletRepository.findById(id);
    }

    @Override
    public List<Long> findListWalletShare(Long id) {
        return shareWalletRepository.findListWalletShare(id);
    }

    @Override
    public List<Long> findWhoWasShared(Long id) {
        return shareWalletRepository.findWhoWasShared(id);
    }

}

package com.codegym.service.wallet;

import com.codegym.model.entity.Wallet;
import com.codegym.repository.IWalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class WalletSV implements IWalletSV{
    @Autowired
    private IWalletRepo walletRepo;

    @Override
    public List<Wallet> findAll() {
        return walletRepo.findAll();
    }

    @Override
    public Wallet save(Wallet wallet) {
        return walletRepo.save(wallet);
    }

    @Override
    public void removeById(Long id) {
        walletRepo.deleteById(id);

    }

    @Override
    public Optional<Wallet> findById(Long id) {
        return walletRepo.findById(id);
    }

    @Override
    public List<Wallet> getWalletByUserId(Long userId) {
        return walletRepo.getWalletByUserId(userId);
    }

    @Override
    public void deleteWallet(Long wallet_id) {
        walletRepo.deleteWallet(wallet_id);
    }

    @Override
    public boolean existsByName(String walletName) {
        return walletRepo.existsByName(walletName);
    }

    @Override
    public Optional<Wallet> findWalletByName(String name) {
        return walletRepo.findWalletByName(name);
    }
}

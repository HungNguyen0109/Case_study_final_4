package com.codegym.service.sumMoney;

import com.codegym.model.dto.SumMoney;
import com.codegym.repository.IWalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SumMoneySV implements ISumMoneySV{
    @Autowired
    private IWalletRepo sumMoneyRepo;

    @Override
    public List<SumMoney> findAll() {
        return null;
    }

    @Override
    public SumMoney save(SumMoney sumMoney) {
        return null;
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public Optional<SumMoney> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<SumMoney> getSumMoney(Long user_id) {
        return sumMoneyRepo.getSumMoney(user_id);
    }
}

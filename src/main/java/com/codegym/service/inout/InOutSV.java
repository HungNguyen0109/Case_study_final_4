package com.codegym.service.inout;

import com.codegym.model.entity.InOut;
import com.codegym.repository.IInOutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class InOutSV implements IInOutSV{
    @Autowired
    private IInOutRepo inOutRepo;
    @Override
    public List<InOut> findAll() {
        return null;
    }

    @Override
    public InOut save(InOut inOut) {
        return inOutRepo.save(inOut);
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public Optional<InOut> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Integer getInFlow(Long idWallet,int day, int month, int year) {
        return inOutRepo.getInFlow(idWallet, day, month, year);
    }

    @Override
    public Integer getOutFlow(Long idWallet,int day, int month, int year) {
        return inOutRepo.getOutFlow(idWallet, day, month, year);
    }

    @Override
    public Integer getMonthOutFlow(Long idWallet, int month, int year) {
        return inOutRepo.getMonthOutFlow(idWallet,month,year);
    }

    @Override
    public Integer getMonthInFlow(Long idWallet, int month, int year) {
        return inOutRepo.getMonthInFlow(idWallet,month,year);
    }

    @Override
    public Integer getIncomeTotal(Long userId) {
        return inOutRepo.getIncomeTotal(userId);
    }

    @Override
    public Integer getOutcomeTotal(Long userId) {
        return inOutRepo.getOutcomeTotal(userId);
    }

    @Override
    public Integer getBalanceTotal(Long userId) {
        return inOutRepo.getBalanceTotal(userId);
    }

    @Override
    public Integer getWalletTotal(Long userId) {
        return inOutRepo.getWalletTotal(userId);
    }
}

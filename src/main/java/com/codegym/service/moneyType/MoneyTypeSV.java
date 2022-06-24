package com.codegym.service.moneyType;

import com.codegym.model.entity.MoneyType;
import com.codegym.repository.IMoneyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MoneyTypeSV implements IMoneyTypeSV{
    @Autowired
    private IMoneyRepo moneyRepo;
    @Override
    public List<MoneyType> findAll() {
        return moneyRepo.findAll();
    }

    @Override
    public MoneyType save(MoneyType moneyType) {
        return moneyRepo.save(moneyType);
    }

    @Override
    public void removeById(Long id) {
        moneyRepo.deleteById(id);

    }

    @Override
    public Optional<MoneyType> findById(Long id) {
        return moneyRepo.findById(id);
    }
}

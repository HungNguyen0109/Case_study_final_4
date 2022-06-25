package com.codegym.service.moneyByCategory;

import com.codegym.model.entity.MoneyByCategory;
import com.codegym.repository.IMoneyCategory;
import com.codegym.service.moneyByCategory.IMoneyByCategorySV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoneyByCategorySV implements IMoneyByCategorySV {

    @Autowired
    private IMoneyCategory iMoneyCategory;

    @Override
    public Iterable<MoneyByCategory> getAllCategoryByUserIDGroup(Long user_id) {
        return iMoneyCategory.getAllCategoryByUserIDGroup(user_id);
    }

    @Override
    public List<MoneyByCategory> findAll() {
        return iMoneyCategory.findAll();
    }

    @Override
    public MoneyByCategory save(MoneyByCategory moneyByCategory) {
        return iMoneyCategory.save(moneyByCategory);
    }

    @Override
    public void removeById(Long id) {
        iMoneyCategory.deleteById(id);
    }

    @Override
    public Optional<MoneyByCategory> findById(Long id) {
        return iMoneyCategory.findById(id);
    }
}




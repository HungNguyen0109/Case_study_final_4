package com.codegym.service.moneyByCategory;

import com.codegym.model.entity.MoneyByCategory;
import com.codegym.service.IGeneralService;

public interface IMoneyByCategorySV extends IGeneralService<MoneyByCategory> {
    Iterable<MoneyByCategory>getAllCategoryByUserIDGroup(Long user_id);
}

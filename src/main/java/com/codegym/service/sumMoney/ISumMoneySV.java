package com.codegym.service.sumMoney;

import com.codegym.model.dto.SumMoney;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface ISumMoneySV extends IGeneralService<SumMoney> {
    List<SumMoney> getSumMoney(Long user_id);
}

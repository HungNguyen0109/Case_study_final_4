package com.codegym.service.addMoney;

import com.codegym.model.entity.AddMoney;
import com.codegym.service.IGeneralService;

import java.util.Date;
import java.util.List;

public interface IAddMoneySV extends IGeneralService<AddMoney> {
    List<AddMoney> getAddMoneyByWallet(Long idWallet);

    Iterable<AddMoney> getListAddMoneyInTimeByIdWallet(Date date1, Date date2, Long idWallet);
    Iterable<AddMoney> getAllAddMoneyByIdUser(Long idUser);
}

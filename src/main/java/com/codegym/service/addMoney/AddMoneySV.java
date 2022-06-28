package com.codegym.service.addMoney;

import com.codegym.model.entity.AddMoney;
import com.codegym.repository.IAddMoneyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class AddMoneySV implements IAddMoneySV {
    @Autowired
    private IAddMoneyRepo addMoneyRepo;
    @Override
    public List<AddMoney> findAll() {
        return addMoneyRepo.findAll() ;
    }

    @Override
    public AddMoney save(AddMoney addMoney) {
        return addMoneyRepo.save(addMoney);
    }

    @Override
    public void removeById(Long id) {
        addMoneyRepo.deleteById(id);
    }

    @Override
    public Optional<AddMoney> findById(Long id) {
        return addMoneyRepo.findById(id);
    }

    @Override
    public List<AddMoney> getAddMoneyByWallet(Long idWallet) {
        return addMoneyRepo.getAddMoneyByWallet(idWallet);
    }

    @Override
    public Iterable<AddMoney> getListAddMoneyInTimeByIdWallet(Date date1, Date date2, Long idWallet) {
        return addMoneyRepo.getListAddMoneyInTimeByIdWallet(date1, date2, idWallet);
    }

    @Override
    public Iterable<AddMoney> getAllAddMoneyByIdUser(Long idUser) {
        return addMoneyRepo.getAllAddMoneyByIdUser(idUser);
    }

    @Override
    public Iterable<AddMoney> getListAddMoneyByUser(Long idUser) {
        return addMoneyRepo.getListAddMoneyByUser(idUser);
    }
}

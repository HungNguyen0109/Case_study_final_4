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
    public Integer getInFlow(Long idWallet, int month, int year) {
        return inOutRepo.getInFlow(idWallet, month, year);
    }

    @Override
    public Integer getOutFlow(Long idWallet, int month, int year) {
        return inOutRepo.getOutFlow(idWallet, month, year);
    }
}

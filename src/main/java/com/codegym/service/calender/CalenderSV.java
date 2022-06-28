package com.codegym.service.calender;

import com.codegym.model.entity.Calender;
import com.codegym.repository.IAddMoneyRepo;
import com.codegym.repository.ICalender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalenderSV implements ICalenderSV {
    @Autowired
    private ICalender calenderRepo;


    @Override
    public List<Calender> findAll() {
        return calenderRepo.findAll();
    }

    @Override
    public Calender save(Calender calender) {
        return calenderRepo.save(calender);
    }

    @Override
    public void removeById(Long id) {
        calenderRepo.deleteById(id);
    }

    @Override
    public Optional<Calender> findById(Long id) {
        return calenderRepo.findById(id);
    }

    @Override
    public Iterable<Calender> getListCalenderInTimeByUser(Long idUser, int day, int month, int year) {
        return calenderRepo.getListCalenderInTimeByUser(idUser,day,month,year);
    }

    @Override
    public Integer getAddMoneyByDay(Long idUser, int day, int month, int year) {
      return calenderRepo.getAddMoneyByDay(idUser,day,month,year);
    }

    @Override
    public Integer getTransactionByDay(Long idUser, int day, int month, int year) {
    return calenderRepo.getTransactionByDay(idUser,day,month,year);
    }
}

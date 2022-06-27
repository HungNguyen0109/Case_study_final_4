package com.codegym.service.calender;

import com.codegym.model.entity.Calender;

import com.codegym.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;


public interface ICalenderSV extends IGeneralService<Calender> {
    Iterable<Calender> getListCalenderInTimeByUser(Long idUser, int day, int month, int year);

    Integer getAddMoneyByDay(Long idUser, int day,int month, int year);

    Integer getTransactionByDay(Long idUser, int day, int month, int year);
}

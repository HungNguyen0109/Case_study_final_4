package com.codegym.repository;

import com.codegym.model.entity.AddMoney;
import com.codegym.model.entity.Calender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICalender extends JpaRepository<Calender, Long> {
    @Query( nativeQuery = true, value = "select * from calender where user_id = ? and Day(date) = ? and Month(date) = ? and Year(date) = ?" )
    Iterable<Calender> getListCalenderInTimeByUser(Long idUser,int day, int month, int year);

    @Query(nativeQuery = true, value = "select sum(money) as inFlow from add_money where user_id = ? and Day(date) = ? and Month(date) = ? and Year(date) = ? ;")
    Integer getAddMoneyByDay(Long idUser, int day,int month, int year);

    @Query(nativeQuery = true, value = "select sum(amount) as outFlow from transactions where user_id = ? and Day(date) = ? and Month(date) = ? and Year(date) = ?;")
    Integer getTransactionByDay(Long idUser, int day, int month, int year);
}

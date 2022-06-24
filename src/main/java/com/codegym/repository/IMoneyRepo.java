package com.codegym.repository;

import com.codegym.model.entity.MoneyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMoneyRepo extends JpaRepository<MoneyType, Long> {
}

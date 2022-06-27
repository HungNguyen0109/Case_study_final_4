package com.codegym.repository;

import com.codegym.model.dto.ShowCategory;
import com.codegym.model.entity.Category;
import com.codegym.model.entity.MoneyByCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IMoneyCategory extends JpaRepository<MoneyByCategory, Long> {

    @Query(nativeQuery = true, value = "select id,sum(amount) as `sum_money`,category_id,user_id from transactions where user_id = ? group by category_id;")
    Iterable<MoneyByCategory> getAllCategoryByUserIDGroup(Long user_id);

//    @Query(nativeQuery = true, value = "insert into categories(name, user_id) values ('?',?);")
//    void createCategory(Category category, Long user_id);
}
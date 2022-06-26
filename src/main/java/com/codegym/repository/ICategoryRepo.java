package com.codegym.repository;

import com.codegym.model.dto.ShowCategory;
import com.codegym.model.entity.Category;
import com.codegym.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepo extends JpaRepository<Category, Long> {

    @Query(nativeQuery = true, value = "select id, name from categories where user_id = ?;")
    Iterable<ShowCategory> getAllCategoryByUserId(Long user_id);

    Optional<Category> findByName(String name);


//    @Query(nativeQuery = true, value = "insert into categories(name, user_id) values ('?',?);")
//    void createCategory(Category category, Long user_id);
}

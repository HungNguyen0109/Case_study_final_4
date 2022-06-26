package com.codegym.service.category;

import com.codegym.model.dto.ShowCategory;
import com.codegym.model.entity.Category;
import com.codegym.model.entity.Transaction;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface ICategorySV extends IGeneralService<Category> {

    Iterable<ShowCategory> getAllCategoryByUserId(Long user_id);


    Optional<Category> findByName(String name);

}

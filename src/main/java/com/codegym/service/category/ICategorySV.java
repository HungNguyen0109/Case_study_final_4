package com.codegym.service.category;

import com.codegym.model.dto.ShowCategory;
import com.codegym.model.entity.Category;
import com.codegym.service.IGeneralService;

public interface ICategorySV extends IGeneralService<Category> {

    Iterable<ShowCategory> getAllCategoryByUserId(Long user_id);

}

package com.ismaildrcn.service;

import java.util.List;

import com.ismaildrcn.model.dto.DtoMenuCategoryRequest;
import com.ismaildrcn.model.dto.DtoMenuCategoryResponse;

public interface IMenuCategoryService {

    DtoMenuCategoryResponse saveMenuCategory(DtoMenuCategoryRequest request);

    List<DtoMenuCategoryResponse> getAllMenuCategories();

}

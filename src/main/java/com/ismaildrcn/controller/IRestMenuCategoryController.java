package com.ismaildrcn.controller;

import java.util.List;

import com.ismaildrcn.model.dto.DtoMenuCategoryRequest;
import com.ismaildrcn.model.dto.DtoMenuCategoryResponse;

public interface IRestMenuCategoryController {

    RootEntity<DtoMenuCategoryResponse> saveMenuCategory(DtoMenuCategoryRequest request);

    RootEntity<List<DtoMenuCategoryResponse>> getAllMenuCategories();

}

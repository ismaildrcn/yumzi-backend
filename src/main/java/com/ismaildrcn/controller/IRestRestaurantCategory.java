package com.ismaildrcn.controller;

import java.util.List;
import java.util.UUID;

import com.ismaildrcn.model.dto.DtoRestaurantCategoryRequest;
import com.ismaildrcn.model.dto.DtoRestaurantCategoryResponse;

public interface IRestRestaurantCategory {

    public RootEntity<DtoRestaurantCategoryResponse> saveRestaurantCategory(DtoRestaurantCategoryRequest request);

    public RootEntity<List<DtoRestaurantCategoryResponse>> getAllRestaurantCategories(Boolean isAsc);

    public RootEntity<DtoRestaurantCategoryResponse> updateRestaurantCategory(UUID uniqueId,
            DtoRestaurantCategoryRequest request);

}

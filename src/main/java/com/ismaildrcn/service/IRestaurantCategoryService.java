package com.ismaildrcn.service;

import java.util.List;
import java.util.UUID;

import com.ismaildrcn.model.dto.DtoRestaurantCategoryRequest;
import com.ismaildrcn.model.dto.DtoRestaurantCategoryResponse;

public interface IRestaurantCategoryService {

    public DtoRestaurantCategoryResponse saveRestaurantCategory(
            DtoRestaurantCategoryRequest request);

    public List<DtoRestaurantCategoryResponse> getAllRestaurantCategories(Boolean isAsc);

    public DtoRestaurantCategoryResponse updateRestaurantCategory(UUID uniqueId,
            DtoRestaurantCategoryRequest request);

}

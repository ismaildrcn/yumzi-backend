package com.ismaildrcn.service;

import com.ismaildrcn.model.dto.DtoRestaurantCategoryRequest;
import com.ismaildrcn.model.dto.DtoRestaurantCategoryResponse;

public interface IRestaurantCategoryService {

    public DtoRestaurantCategoryResponse saveRestaurantCategory(
            DtoRestaurantCategoryRequest dtoRestaurantCategoryRequest);

}

package com.ismaildrcn.service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildrcn.model.dto.DtoRestaurantCategoryRequest;
import com.ismaildrcn.model.dto.DtoRestaurantCategoryResponse;
import com.ismaildrcn.model.entity.RestaurantCategory;
import com.ismaildrcn.repository.RestaurantCategoryRepository;
import com.ismaildrcn.service.IRestaurantCategoryService;

@Service
public class IRestaurantCategoryServiceImpl implements IRestaurantCategoryService {

    @Autowired
    RestaurantCategoryRepository restaurantCategoryRepository;

    @Override
    public DtoRestaurantCategoryResponse saveRestaurantCategory(
            DtoRestaurantCategoryRequest restaurantCategoryRequest) {
        RestaurantCategory restaurantCategory = createRestaurantCategoryFromDto(restaurantCategoryRequest);

        DtoRestaurantCategoryResponse dtoRestaurantCategoryResponse = new DtoRestaurantCategoryResponse();
        BeanUtils.copyProperties(restaurantCategory, dtoRestaurantCategoryResponse);

        return dtoRestaurantCategoryResponse;
    }

    private RestaurantCategory createRestaurantCategoryFromDto(
            DtoRestaurantCategoryRequest restaurantCategoryRequest) {
        RestaurantCategory restaurantCategory = new RestaurantCategory();
        BeanUtils.copyProperties(restaurantCategoryRequest, restaurantCategory);

        if (restaurantCategory.getSortOrder() == null) {
            Integer maxSortOrder = restaurantCategoryRepository.findMaxSortOrder();
            restaurantCategory.setSortOrder((maxSortOrder == null ? 0 : maxSortOrder) + 1);
        }

        restaurantCategory = restaurantCategoryRepository.save(restaurantCategory);
        return restaurantCategory;
    }

}

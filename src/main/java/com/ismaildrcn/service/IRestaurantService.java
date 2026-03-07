package com.ismaildrcn.service;

import java.util.List;
import java.util.UUID;

import com.ismaildrcn.model.dto.DtoRestaurantRequest;
import com.ismaildrcn.model.dto.DtoRestaurantResponse;
import com.ismaildrcn.model.dto.DtoRestaurantSummary;
import com.ismaildrcn.model.entity.User;

public interface IRestaurantService {

    DtoRestaurantResponse saveRestaurant(DtoRestaurantRequest request);

    DtoRestaurantResponse getRestaurantByUniqueId(User user, UUID uniqueId);

    DtoRestaurantResponse updateRestaurantByUniqueId(UUID uniqueId, DtoRestaurantRequest request);

    void deleteRestaurantByUniqueId(UUID uniqueId);

    List<DtoRestaurantSummary> getAllRestaurants(User user);

    List<DtoRestaurantSummary> findRestaurantsByCategoryId(User user, UUID categoryId);
}

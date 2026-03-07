package com.ismaildrcn.controller;

import java.util.List;
import java.util.UUID;

import com.ismaildrcn.model.dto.DtoRestaurantRequest;
import com.ismaildrcn.model.dto.DtoRestaurantResponse;
import com.ismaildrcn.model.dto.DtoRestaurantSummary;
import com.ismaildrcn.model.entity.User;

public interface IRestRestaurantController {

    RootEntity<DtoRestaurantResponse> saveRestaurant(DtoRestaurantRequest request);

    RootEntity<DtoRestaurantResponse> getRestaurantByUniqueId(User user, UUID uniqueId);

    RootEntity<DtoRestaurantResponse> updateRestaurantByUniqueId(UUID uniqueId, DtoRestaurantRequest request);

    RootEntity<?> deleteRestaurantByUniqueId(UUID uniqueId);

    RootEntity<List<DtoRestaurantSummary>> getAllRestaurants(User user);

    RootEntity<List<DtoRestaurantSummary>> findRestaurantsByCategoryId(User user, UUID categoryId);
}

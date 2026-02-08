package com.ismaildrcn.controller;

import java.util.List;
import java.util.UUID;

import com.ismaildrcn.model.dto.DtoRestaurantRequest;
import com.ismaildrcn.model.dto.DtoRestaurantResponse;
import com.ismaildrcn.model.dto.DtoRestaurantSummary;

public interface IRestRestaurantController {

    RootEntity<DtoRestaurantResponse> saveRestaurant(DtoRestaurantRequest request);

    RootEntity<DtoRestaurantResponse> getRestaurantByUniqueId(UUID uniqueId);

    RootEntity<DtoRestaurantResponse> updateRestaurantByUniqueId(UUID uniqueId, DtoRestaurantRequest request);

    RootEntity<?> deleteRestaurantByUniqueId(UUID uniqueId);

    RootEntity<List<DtoRestaurantSummary>> getAllRestaurants();
}

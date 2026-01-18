package com.ismaildrcn.controller;

import java.util.UUID;

import com.ismaildrcn.model.dto.DtoRestaurantRequest;
import com.ismaildrcn.model.dto.DtoRestaurantResponse;

public interface IRestRestaurantController {

    RootEntity<DtoRestaurantResponse> saveRestaurant(DtoRestaurantRequest request);

    RootEntity<DtoRestaurantResponse> getRestaurantByUniqueId(UUID uniqueId);

    RootEntity<DtoRestaurantResponse> updateRestaurantByUniqueId(UUID uniqueId, DtoRestaurantRequest request);

    RootEntity<?> deleteRestaurantByUniqueId(UUID uniqueId);

}

package com.ismaildrcn.service;

import java.util.UUID;

import com.ismaildrcn.model.dto.DtoRestaurantRequest;
import com.ismaildrcn.model.dto.DtoRestaurantResponse;

public interface IRestaurantService {

    DtoRestaurantResponse saveRestaurant(DtoRestaurantRequest request);

    DtoRestaurantResponse getRestaurantByUniqueId(UUID uniqueId);

    DtoRestaurantResponse updateRestaurantByUniqueId(UUID uniqueId, DtoRestaurantRequest request);

    void deleteRestaurantByUniqueId(UUID uniqueId);
}

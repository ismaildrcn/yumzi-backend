package com.ismaildrcn.controller;

import java.util.List;
import java.util.UUID;

import com.ismaildrcn.model.dto.DtoRestaurantCuisineRequest;
import com.ismaildrcn.model.dto.DtoRestaurantCuisineResponse;

public interface IRestRestaurantCuisineController {

    public RootEntity<List<DtoRestaurantCuisineResponse>> getAllCuisines(boolean isAsc);

    public RootEntity<DtoRestaurantCuisineResponse> saveRestaurantCuisine(DtoRestaurantCuisineRequest request);

    public RootEntity<DtoRestaurantCuisineResponse> updateRestaurantCuisine(UUID uniqueId,
            DtoRestaurantCuisineRequest request);

}

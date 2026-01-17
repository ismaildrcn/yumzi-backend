package com.ismaildrcn.service;

import java.util.List;
import java.util.UUID;

import com.ismaildrcn.model.dto.DtoRestaurantCuisineRequest;
import com.ismaildrcn.model.dto.DtoRestaurantCuisineResponse;

public interface IRestaurantCuisineService {

    public List<DtoRestaurantCuisineResponse> getAllCuisines(boolean isAsc);

    public DtoRestaurantCuisineResponse saveRestaurantCuisine(DtoRestaurantCuisineRequest request);

    public DtoRestaurantCuisineResponse updateRestaurantCuisine(UUID uniqueId, DtoRestaurantCuisineRequest request);
}

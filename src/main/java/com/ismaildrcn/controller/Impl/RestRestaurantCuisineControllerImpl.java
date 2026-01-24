package com.ismaildrcn.controller.Impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildrcn.controller.IRestRestaurantCuisineController;
import com.ismaildrcn.controller.RestBaseController;
import com.ismaildrcn.controller.RootEntity;
import com.ismaildrcn.model.dto.DtoRestaurantCuisineRequest;
import com.ismaildrcn.model.dto.DtoRestaurantCuisineResponse;
import com.ismaildrcn.service.IRestaurantCuisineService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/restaurant/cuisine")
public class RestRestaurantCuisineControllerImpl extends RestBaseController
        implements IRestRestaurantCuisineController {

    @Autowired
    private IRestaurantCuisineService restaurantCuisineService;

    @Override
    @GetMapping("/all")
    public RootEntity<List<DtoRestaurantCuisineResponse>> getAllCuisines(
            @RequestParam(defaultValue = "true") boolean isAsc) {
        return ok(restaurantCuisineService.getAllCuisines(isAsc));
    }

    @Override
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RootEntity<DtoRestaurantCuisineResponse> saveRestaurantCuisine(
            @Valid @RequestBody DtoRestaurantCuisineRequest request) {
        return ok(restaurantCuisineService.saveRestaurantCuisine(request));
    }

    @Override
    @PatchMapping("/update/{uniqueId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RootEntity<DtoRestaurantCuisineResponse> updateRestaurantCuisine(@PathVariable UUID uniqueId,
            @Valid @RequestBody DtoRestaurantCuisineRequest request) {
        return ok(restaurantCuisineService.updateRestaurantCuisine(uniqueId, request));
    }

}

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

import com.ismaildrcn.controller.IRestRestaurantCategoryController;
import com.ismaildrcn.controller.RestBaseController;
import com.ismaildrcn.controller.RootEntity;
import com.ismaildrcn.model.dto.DtoRestaurantCategoryRequest;
import com.ismaildrcn.model.dto.DtoRestaurantCategoryResponse;
import com.ismaildrcn.service.IRestaurantCategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/restaurant/category")
public class RestRestaurantCategoryControllerImpl extends RestBaseController
        implements IRestRestaurantCategoryController {

    @Autowired
    private IRestaurantCategoryService restaurantCategoryService;

    @Override
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RootEntity<DtoRestaurantCategoryResponse> saveRestaurantCategory(
            @Valid @RequestBody DtoRestaurantCategoryRequest request) {
        return ok(restaurantCategoryService.saveRestaurantCategory(request));
    }

    @Override
    @GetMapping("/all")
    public RootEntity<List<DtoRestaurantCategoryResponse>> getAllRestaurantCategories(
            @RequestParam(defaultValue = "true") Boolean isAsc) {
        return ok(restaurantCategoryService.getAllRestaurantCategories(isAsc));
    }

    @Override
    @PatchMapping("/update/{uniqueId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RootEntity<DtoRestaurantCategoryResponse> updateRestaurantCategory(@PathVariable UUID uniqueId,
            @Valid @RequestBody DtoRestaurantCategoryRequest request) {
        return ok(restaurantCategoryService.updateRestaurantCategory(uniqueId, request));
    }

}

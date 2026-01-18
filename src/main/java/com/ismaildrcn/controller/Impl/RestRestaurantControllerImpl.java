package com.ismaildrcn.controller.Impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildrcn.controller.IRestRestaurantController;
import com.ismaildrcn.controller.RestBaseController;
import com.ismaildrcn.controller.RootEntity;
import com.ismaildrcn.model.dto.DtoRestaurantRequest;
import com.ismaildrcn.model.dto.DtoRestaurantResponse;
import com.ismaildrcn.service.IRestaurantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/restaurant")
public class RestRestaurantControllerImpl extends RestBaseController implements IRestRestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @Override
    @PostMapping("/save")
    public RootEntity<DtoRestaurantResponse> saveRestaurant(@Valid @RequestBody DtoRestaurantRequest request) {
        return ok(restaurantService.saveRestaurant(request));
    }

    @Override
    @GetMapping("/{uniqueId}")
    public RootEntity<DtoRestaurantResponse> getRestaurantByUniqueId(@PathVariable UUID uniqueId) {
        return ok(restaurantService.getRestaurantByUniqueId(uniqueId));
    }

    @Override
    @PatchMapping("/{uniqueId}")
    public RootEntity<DtoRestaurantResponse> updateRestaurantByUniqueId(@PathVariable UUID uniqueId,
            @Valid @RequestBody DtoRestaurantRequest request) {
        return ok(restaurantService.updateRestaurantByUniqueId(uniqueId, request));
    }

}

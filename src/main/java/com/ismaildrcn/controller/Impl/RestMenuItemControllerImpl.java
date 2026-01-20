package com.ismaildrcn.controller.Impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildrcn.controller.IRestMenuItemController;
import com.ismaildrcn.controller.RestBaseController;
import com.ismaildrcn.controller.RootEntity;
import com.ismaildrcn.model.dto.DtoMenuItemRequest;
import com.ismaildrcn.model.dto.DtoMenuItemResponse;
import com.ismaildrcn.service.IMenuItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/restaurant/menu-items")
public class RestMenuItemControllerImpl extends RestBaseController implements IRestMenuItemController {

    @Autowired
    IMenuItemService menuItemService;

    @Override
    @PostMapping("/{restaurantUniqueId}")
    public RootEntity<DtoMenuItemResponse> saveMenuItem(@PathVariable UUID restaurantUniqueId,
            @Valid @RequestBody DtoMenuItemRequest request) {
        return ok(menuItemService.saveMenuItem(restaurantUniqueId, request));
    }

    @Override
    @GetMapping("/{restaurantUniqueId}")
    public RootEntity<List<DtoMenuItemResponse>> findMenuItemsByRestaurantId(@PathVariable UUID restaurantUniqueId) {
        return ok(menuItemService.findMenuItemsByRestaurantId(restaurantUniqueId));
    }

}

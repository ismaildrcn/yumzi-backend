package com.ismaildrcn.controller.Impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping("/rest/api/restaurant/")
public class RestMenuItemControllerImpl extends RestBaseController implements IRestMenuItemController {

    @Autowired
    IMenuItemService menuItemService;

    @Override
    @PostMapping("/{restaurantId}/menu-items")
    public RootEntity<DtoMenuItemResponse> saveMenuItem(@PathVariable UUID restaurantId,
            @Valid @RequestBody DtoMenuItemRequest request) {
        return ok(menuItemService.saveMenuItem(restaurantId, request));
    }

    @Override
    @GetMapping("/{restaurantId}/menu-items")
    public RootEntity<List<DtoMenuItemResponse>> findMenuItemsByRestaurantId(@PathVariable UUID restaurantId) {
        return ok(menuItemService.findMenuItemsByRestaurantId(restaurantId));
    }

    @Override
    @PatchMapping("/{restaurantId}/menu-items/{menuItemId}")
    public RootEntity<DtoMenuItemResponse> updateMenuItem(@PathVariable UUID restaurantId,
            @PathVariable UUID menuItemId,
            @Valid @RequestBody DtoMenuItemRequest request) {
        return ok(menuItemService.updateMenuItem(restaurantId, menuItemId, request));
    }

    @Override
    @DeleteMapping("/{restaurantId}/menu-items/{menuItemId}")
    public RootEntity<?> deleteMenuItem(@PathVariable UUID restaurantId, @PathVariable UUID menuItemId) {
        menuItemService.deleteMenuItem(restaurantId, menuItemId);
        return ok("Menu item deleted successfully");
    }

}

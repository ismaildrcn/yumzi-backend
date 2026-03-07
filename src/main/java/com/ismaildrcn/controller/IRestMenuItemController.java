package com.ismaildrcn.controller;

import java.util.List;
import java.util.UUID;

import com.ismaildrcn.model.dto.DtoMenuItemRequest;
import com.ismaildrcn.model.dto.DtoMenuItemResponse;
import com.ismaildrcn.model.entity.User;

public interface IRestMenuItemController {

    RootEntity<DtoMenuItemResponse> saveMenuItem(UUID restaurantUniqueId, DtoMenuItemRequest request);

    RootEntity<List<DtoMenuItemResponse>> findMenuItemsByRestaurantId(UUID restaurantUniqueId);

    RootEntity<DtoMenuItemResponse> updateMenuItem(UUID restaurantId, UUID menuItemId,
            DtoMenuItemRequest request);

    RootEntity<?> deleteMenuItem(UUID restaurantId, UUID menuItemId);

    RootEntity<List<DtoMenuItemResponse>> findMenuItemsByMenuCategory(UUID restaurantId, UUID menuCategoryId);

    RootEntity<DtoMenuItemResponse> findMenuItemById(User user, UUID menuItemId);

}

package com.ismaildrcn.controller;

import java.util.List;
import java.util.UUID;

import com.ismaildrcn.model.dto.DtoMenuItemRequest;
import com.ismaildrcn.model.dto.DtoMenuItemResponse;

public interface IRestMenuItemController {

    RootEntity<DtoMenuItemResponse> saveMenuItem(UUID restaurantUniqueId, DtoMenuItemRequest request);

    RootEntity<List<DtoMenuItemResponse>> findMenuItemsByRestaurantId(UUID restaurantUniqueId);

}

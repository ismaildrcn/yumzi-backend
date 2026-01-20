package com.ismaildrcn.service;

import java.util.List;
import java.util.UUID;

import com.ismaildrcn.model.dto.DtoMenuItemRequest;
import com.ismaildrcn.model.dto.DtoMenuItemResponse;

public interface IMenuItemService {

    DtoMenuItemResponse saveMenuItem(UUID restaurantUniqueId, DtoMenuItemRequest request);

    List<DtoMenuItemResponse> findMenuItemsByRestaurantId(UUID restaurantUniqueId);

}

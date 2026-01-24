package com.ismaildrcn.controller;

import com.ismaildrcn.model.dto.DtoCartItemRequest;
import com.ismaildrcn.model.dto.DtoCartResponse;
import com.ismaildrcn.model.entity.CartItem;
import com.ismaildrcn.model.entity.User;

public interface ICartController {

    RootEntity<CartItem> addItemToCart(DtoCartItemRequest request, User user);

    RootEntity<DtoCartResponse> getUserCart(User user);

}

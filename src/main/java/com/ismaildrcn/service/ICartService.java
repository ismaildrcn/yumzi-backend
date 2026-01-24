package com.ismaildrcn.service;

import com.ismaildrcn.model.dto.DtoCartItemRequest;
import com.ismaildrcn.model.dto.DtoCartResponse;
import com.ismaildrcn.model.entity.CartItem;
import com.ismaildrcn.model.entity.User;

public interface ICartService {

    CartItem addItemToCart(DtoCartItemRequest request, User user);

    DtoCartResponse getUserCart(User user);

}

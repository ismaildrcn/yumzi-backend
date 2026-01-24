package com.ismaildrcn.controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildrcn.controller.ICartController;
import com.ismaildrcn.controller.RestBaseController;
import com.ismaildrcn.model.dto.DtoCartItemRequest;
import com.ismaildrcn.model.entity.CartItem;
import com.ismaildrcn.model.entity.User;
import com.ismaildrcn.service.ICartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/cart")
public class CartControllerImpl extends RestBaseController implements ICartController {

    @Autowired
    private ICartService cartService;

    @Override
    @PostMapping("/add-item")
    public CartItem addItemToCart(
            @Valid @RequestBody DtoCartItemRequest request,
            @AuthenticationPrincipal User user) {
        return cartService.addItemToCart(request, user);
    }

}

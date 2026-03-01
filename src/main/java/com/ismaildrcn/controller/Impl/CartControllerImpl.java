package com.ismaildrcn.controller.Impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildrcn.controller.ICartController;
import com.ismaildrcn.controller.RestBaseController;
import com.ismaildrcn.controller.RootEntity;
import com.ismaildrcn.model.dto.DtoCartItemRequest;
import com.ismaildrcn.model.dto.DtoCartResponse;
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
    public RootEntity<CartItem> addItemToCart(
            @Valid @RequestBody DtoCartItemRequest request,
            @AuthenticationPrincipal User user) {
        return ok(cartService.addItemToCart(request, user));
    }

    @Override
    @GetMapping("/my-cart")
    public RootEntity<DtoCartResponse> getUserCart(@AuthenticationPrincipal User user) {
        return ok(cartService.getUserCart(user));
    }

    @Override
    @DeleteMapping("/cart-items/{menuItemId}")
    public RootEntity<?> removeCartItem(@AuthenticationPrincipal User user, @PathVariable UUID menuItemId) {
        return ok(cartService.removeCartItem(user, menuItemId));
    }

    @Override
    @PatchMapping("/update-item")
    public RootEntity<DtoCartResponse> updateMenuItemQuantity(@AuthenticationPrincipal User user,
            @Valid @RequestBody DtoCartItemRequest request) {
        return ok(cartService.updateMenuItemQuantity(user, request));
    }
}

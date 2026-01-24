package com.ismaildrcn.service.Impl;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildrcn.exception.BaseException;
import com.ismaildrcn.exception.ErrorMessage;
import com.ismaildrcn.exception.MessageType;
import com.ismaildrcn.model.dto.DtoCartItemRequest;
import com.ismaildrcn.model.entity.Cart;
import com.ismaildrcn.model.entity.CartItem;
import com.ismaildrcn.model.entity.MenuItem;
import com.ismaildrcn.model.entity.User;
import com.ismaildrcn.repository.CartRepository;
import com.ismaildrcn.repository.MenuItemRepository;
import com.ismaildrcn.service.ICartService;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public CartItem addItemToCart(DtoCartItemRequest request, User user) {

        MenuItem menuItem = menuItemRepository.findByUniqueId(request.getMenuItemId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_FOUND)));
        Cart userCart = getUserCartFromCache(user);
        CartItem cartItem = createCartItemFromMenuItem(menuItem, request);
        userCart.getCartItems().add(cartItem);
        cartRepository.save(userCart);
        return cartItem;
    }

    @Override
    public Cart getUserCart(User user) {
        return null;
    }

    private CartItem createCartItemFromMenuItem(MenuItem menuItem, DtoCartItemRequest request) {
        CartItem cartItem = new CartItem();
        BeanUtils.copyProperties(menuItem, cartItem);
        cartItem.setMenuItemId(menuItem.getUniqueId());
        cartItem.setQuantity(request.getQuantity());
        cartItem.setUnitPrice(menuItem.getPrice());
        cartItem.setDiscountedUnitPrice(menuItem.getDiscountPrice());
        if (menuItem.getDiscountPrice() != null) {
            cartItem.setTotalPrice(cartItem.getDiscountedUnitPrice().multiply(
                    BigDecimal.valueOf(cartItem.getQuantity())));
        } else {
            cartItem.setTotalPrice(menuItem.getPrice().multiply(
                    BigDecimal.valueOf(cartItem.getQuantity())));
        }
        cartItem.setSpecialInstructions(request.getSpecialInstructions());
        return cartItem;
    }

    private Cart getUserCartFromCache(User user) {
        String cartId = "user:" + user.getUniqueId();
        return cartRepository.findById(cartId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setId(cartId);
                    newCart.setUserId(user.getUniqueId());
                    return newCart;
                });
    }

}

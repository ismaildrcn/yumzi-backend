package com.ismaildrcn.service.Impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildrcn.exception.BaseException;
import com.ismaildrcn.exception.ErrorMessage;
import com.ismaildrcn.exception.MessageType;
import com.ismaildrcn.model.dto.DtoCartItemRequest;
import com.ismaildrcn.model.dto.DtoCartResponse;
import com.ismaildrcn.model.dto.DtoRestaurantSummary;
import com.ismaildrcn.model.entity.Cart;
import com.ismaildrcn.model.entity.CartItem;
import com.ismaildrcn.model.entity.MenuItem;
import com.ismaildrcn.model.entity.Restaurant;
import com.ismaildrcn.model.entity.User;
import com.ismaildrcn.repository.CartRepository;
import com.ismaildrcn.repository.MenuItemRepository;
import com.ismaildrcn.repository.RestaurantRepository;
import com.ismaildrcn.service.ICartService;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public CartItem addItemToCart(DtoCartItemRequest request, User user) {

        MenuItem menuItem = menuItemRepository.findByUniqueId(request.getMenuItemId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_FOUND)));
        Cart userCart = getUserCartFromCache(user);
        CartItem cartItem = createCartItemFromMenuItem(menuItem, request);
        userCart.getCartItems().add(cartItem);

        // Toplam hesaplama (fiyatlar zaten vergi dahil)
        BigDecimal totalWithTax = BigDecimal.ZERO;
        for (CartItem item : userCart.getCartItems()) {
            totalWithTax = totalWithTax.add(item.getTotalPrice());
        }

        // Vergi dahil fiyattan vergi miktarını hesapla
        // totalWithTax = vergisiz * 1.01
        // vergisiz = totalWithTax / 1.01
        BigDecimal subTotal = totalWithTax.divide(new BigDecimal("1.01"), 2, RoundingMode.HALF_UP);
        BigDecimal taxAmount = totalWithTax.subtract(subTotal);

        userCart.setSubTotal(subTotal); // Vergisiz tutar (backend için)
        userCart.setTaxAmount(taxAmount); // Vergi tutarı (backend için)
        userCart.setTotalAmount(totalWithTax); // Vergi dahil toplam (kullanıcının ödeyeceği)
        userCart.setRestaurantId(menuItem.getRestaurant().getUniqueId());

        cartRepository.save(userCart);
        return cartItem;
    }

    @Override
    public DtoCartResponse getUserCart(User user) {
        Cart cart = getUserCartFromCache(user);

        DtoCartResponse response = new DtoCartResponse();
        BeanUtils.copyProperties(cart, response);

        BigDecimal subTotal = cart.getCartItems().stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        response.setSubTotal(subTotal);
        response.setTotalAmount(cart.getTotalAmount());
        Restaurant restaurant = restaurantRepository.findByUniqueId(cart.getRestaurantId())
                .orElse(null);
        DtoRestaurantSummary dtoRestaurant = new DtoRestaurantSummary();
        if (restaurant != null) {
            BeanUtils.copyProperties(restaurant, dtoRestaurant);
        }
        response.setRestaurant(dtoRestaurant);
        return response;
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

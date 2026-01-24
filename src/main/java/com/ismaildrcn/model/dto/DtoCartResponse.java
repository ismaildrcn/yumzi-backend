package com.ismaildrcn.model.dto;

import java.math.BigDecimal;
import java.util.List;

import com.ismaildrcn.model.entity.CartItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCartResponse {

    private BigDecimal subTotal;

    private BigDecimal deliveryFee;

    private BigDecimal taxAmount;

    private BigDecimal serviceFee;

    private BigDecimal discountAmount;

    private BigDecimal totalAmount;

    private String couponCode;

    private BigDecimal couponDiscount;

    private List<CartItem> cartItems;

    private DtoRestaurantSummary restaurant;

}

package com.ismaildrcn.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.ismaildrcn.model.embeddable.OpeningHours;
import com.ismaildrcn.model.entity.Address;
import com.ismaildrcn.model.entity.RestaurantCategory;
import com.ismaildrcn.model.entity.RestaurantCuisine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoRestaurantResponse {

    private UUID uniqueId;

    private String name;

    private String slug;

    private String description;

    private String phoneNumber;

    private String email;

    private String website;

    private OpeningHours openingHours;

    private String deliveryTimeRange;

    private BigDecimal minimumOrderAmount;

    private BigDecimal deliveryFee;

    private double deliveryRadiusKm;

    private boolean isActive;

    private boolean isAcceptingOrders;

    private boolean isFeatured;

    private double rating;

    private Integer reviewCount;

    private Integer orderCount;

    private String logoUrl;

    private String coverImageUrl;

    private String taxNumber;

    private RestaurantCategory category;

    private RestaurantCuisine cuisine;

    private Address address;

}

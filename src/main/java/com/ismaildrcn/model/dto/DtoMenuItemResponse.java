package com.ismaildrcn.model.dto;

import java.util.UUID;

import com.ismaildrcn.model.embeddable.Allergens;
import com.ismaildrcn.model.entity.MenuCategory;
import com.ismaildrcn.model.entity.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoMenuItemResponse {

    private UUID uniqueId;

    private String name;

    private String description;

    private String slug;

    private String price;

    private String discountPrice;

    private double discountPercentage;

    private String currency;

    private boolean isActive;

    private boolean isAvailable;

    private boolean isFeatured;

    private boolean isVegetarian;

    private boolean isVegan;

    private boolean isGlutenFree;

    private boolean isSpicy;

    private Integer stockQuantity;

    private Integer minimumOrderQuantity;

    private Integer maximumOrderQuantity;

    private String preparationTime;

    private int sortOrder;

    private String imageUrl;

    private String thumbnailUrl;

    private Integer calories;

    private double proteinGrams;

    private double carbohydrateGrams;

    private double fatGrams;

    private Allergens allergens;

    private int totalOrders;

    private double averageRating;

    private Integer reviewCount;

    private Restaurant restaurant;

    private MenuCategory category;

}

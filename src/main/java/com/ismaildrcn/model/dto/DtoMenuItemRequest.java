package com.ismaildrcn.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.ismaildrcn.model.embeddable.Allergens;
import com.ismaildrcn.model.enums.CurrencyType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoMenuItemRequest {

    @NotNull(message = "Name cannot be null")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    private BigDecimal discountPrice;

    private double discountPercentage;

    @NotNull(message = "Currency cannot be null")
    private CurrencyType currency;

    @NotNull(message = "Active status cannot be null")
    private boolean isActive;

    @NotNull(message = "Availability status cannot be null")
    private boolean isAvailable;

    @NotNull(message = "Featured status cannot be null")
    private boolean isFeatured;

    @NotNull(message = "Vegetarian status cannot be null")
    private boolean isVegetarian;

    @NotNull(message = "Vegan status cannot be null")
    private boolean isVegan;

    @NotNull(message = "Gluten-free status cannot be null")
    private boolean isGlutenFree;

    @NotNull(message = "Spicy status cannot be null")
    private boolean isSpicy;

    @NotNull(message = "Stock quantity cannot be null")
    private Integer stockQuantity;

    @NotNull(message = "Minimum order quantity cannot be null")
    private Integer minimumOrderQuantity;

    @NotNull(message = "Maximum order quantity cannot be null")
    private Integer maximumOrderQuantity;

    @NotNull(message = "Preparation time cannot be null")
    private String preparationTime;

    @NotNull(message = "Image URL cannot be null")
    private String imageUrl;

    @NotNull(message = "Thumbnail URL cannot be null")
    private String thumbnailUrl;

    private Integer calories;

    private double proteinGrams;

    private double carbohydrateGrams;

    private double fatGrams;

    private Allergens allergens;

    private UUID restaurantId;

    private UUID categoryId;
}

package com.ismaildrcn.model.entity;

import java.math.BigDecimal;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.ismaildrcn.model.embeddable.Allergens;
import com.ismaildrcn.model.enums.CurrencyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu_items", indexes = {
        @Index(name = "idx_menu_items_restaurant_id", columnList = "restaurant_id"),
        @Index(name = "idx_menu_items_category_id", columnList = "category_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenuItem extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "slug", nullable = false, length = 150)
    private String slug;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "discount_price")
    private BigDecimal discountPrice;

    @Column(name = "discount_percentage")
    private double discountPercentage;

    @Column(name = "currency", length = 10)
    private CurrencyType currency;

    @Column(name = "is_active")
    @ColumnDefault("true")
    private boolean isActive;

    @Column(name = "is_available")
    @ColumnDefault("true")
    private boolean isAvailable;

    @Column(name = "is_featured")
    @ColumnDefault("false")
    private boolean isFeatured;

    @Column(name = "is_vegetarian")
    @ColumnDefault("false")
    private boolean isVegetarian;

    @Column(name = "is_vegan")
    @ColumnDefault("false")
    private boolean isVegan;

    @Column(name = "is_gluten_free")
    @ColumnDefault("false")
    private boolean isGlutenFree;

    @Column(name = "is_spicy")
    @ColumnDefault("false")
    private boolean isSpicy;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "minimum_order_quantity")
    private Integer minimumOrderQuantity;

    @Column(name = "maximum_order_quantity")
    private Integer maximumOrderQuantity;

    @Column(name = "preparation_time")
    private String preparationTime;

    @Column(name = "sort_order", nullable = false)
    private int sortOrder = 999;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "calories")
    private Integer calories;

    @Column(name = "protein_grams")
    private double proteinGrams;

    @Column(name = "carbohydrate_grams")
    private double carbohydrateGrams;

    @Column(name = "fat_grams")
    private double fatGrams;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "allergens", columnDefinition = "jsonb")
    private Allergens allergens;

    @Column(name = "total_orders")
    private int totalOrders;

    @Column(name = "average_rating")
    private double averageRating;

    @Column(name = "review_count")
    private Integer reviewCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private MenuCategory category;

    public BigDecimal getFinalPrice() {
        if (discountPrice != null && discountPrice.compareTo(BigDecimal.ZERO) > 0) {
            return discountPrice;
        }
        return price;
    }

    public boolean hasDiscaunt() {
        return (discountPrice != null && discountPrice.compareTo(price) < 0);
    }

}

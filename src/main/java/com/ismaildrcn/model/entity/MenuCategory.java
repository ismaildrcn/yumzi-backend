package com.ismaildrcn.model.entity;

import com.ismaildrcn.model.enums.MenuCategoryType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "menu_categories", indexes = {
        @Index(name = "idx_menu_categories_restaurant_id", columnList = "restaurant_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenuCategory extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "slug", nullable = false, length = 150)
    private String slug;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "sort_order")
    // Default olarak 999 atandi. Yeni eklenen kategoriler sona eklenir.
    private Integer sortOrder = 999;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_featured")
    private Boolean isFeatured;

    @Column(name = "minimum_order_quantity")
    private Integer minimumOrderQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_type", length = 50)
    private MenuCategoryType categoryType = MenuCategoryType.STANDARD;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

}

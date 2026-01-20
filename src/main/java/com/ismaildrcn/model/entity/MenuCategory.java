package com.ismaildrcn.model.entity;

import com.ismaildrcn.model.enums.MenuCategoryType;
import com.ismaildrcn.utils.SlugUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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

    // Default olarak 999 atandi. Yeni eklenen kategoriler sona eklenir.
    @Column(name = "sort_order")
    private Integer sortOrder = 999;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_featured")
    private Boolean isFeatured;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_type", length = 50)
    private MenuCategoryType categoryType;

    @PrePersist
    @PreUpdate
    private void generatedSlugFromName() {
        if (this.name != null) {
            this.slug = SlugUtils.generateSlug(this.name);
        }
    }

}

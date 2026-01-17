package com.ismaildrcn.model.entity;

import com.ismaildrcn.utils.SlugUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurant_cuisines")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RestaurantCuisine extends BaseEntity {

    private String name;

    private String slug;

    private String description;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "is_active")
    private boolean isActive = true;

    @PrePersist
    @PreUpdate
    private void generatedSlugFromName() {
        if (this.name != null && !this.name.isEmpty()) {
            this.slug = SlugUtils.generateSlug(this.name);
        }
    }

}

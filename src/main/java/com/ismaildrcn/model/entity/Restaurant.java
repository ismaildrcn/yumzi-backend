package com.ismaildrcn.model.entity;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.ismaildrcn.model.embeddable.BankAccounts;
import com.ismaildrcn.model.embeddable.OpeningHours;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurants", indexes = {
        @Index(name = "idx_restaurants_category_id", columnList = "category_id"),
        @Index(name = "idx_restaurants_cuisine_id", columnList = "cuisine_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Restaurant extends BaseEntity {

    private String name;

    private String slug;

    private String description;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    private String website;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "opening_hours", columnDefinition = "jsonb")
    private OpeningHours openingHours;

    @Column(name = "delivery_time_range")
    private String deliveryTimeRange;

    @Column(name = "minimum_order_amount")
    private BigDecimal minimumOrderAmount = BigDecimal.ZERO;

    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee = BigDecimal.ZERO;

    @Column(name = "delivery_radius_km")
    private double deliveryRadiusKm;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(name = "is_accepting_orders")
    private boolean isAcceptingOrders = true;

    @Column(name = "is_featured")
    private boolean isFeatured;

    private double rating = 0.0;

    @Column(name = "review_count")
    private Integer reviewCount = 0;

    @Column(name = "order_count")
    private Integer orderCount = 0;

    @Column(name = "logo_url", nullable = true)
    private String logoUrl;

    @Column(name = "cover_image_url", nullable = true)
    private String coverImageUrl;

    @Column(name = "tax_number")
    private String taxNumber;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "bank_account_info", columnDefinition = "jsonb")
    private BankAccounts bankAccountInfo;

    @OneToOne(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private RestaurantCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuisine_id")
    private RestaurantCuisine cuisine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuisine_id")

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;
}

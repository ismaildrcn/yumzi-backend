package com.ismaildrcn.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderItem extends BaseEntity {

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "item_image_url")
    private String itemImageUrl;

    @Column(name = "quantity", nullable = false)
    private Integer quantity = 1;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "discounted_unit_price", precision = 10, scale = 2)
    private BigDecimal discountedUnitPrice;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "special_instructions")
    private String specialInstructions;

    @Column(name = "selected_options", columnDefinition = "JSONB")
    private String selectedOptions;

    @Column(name = "calories")
    private Integer calories;

    @Column(name = "is_vegetarian")
    private Boolean isVegetarian = false;

    @Column(name = "is_spicy")
    private Boolean isSpicy = false;

    @Column(name = "is_cancelled")
    private Boolean isCancelled = false;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "review")
    private String review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}

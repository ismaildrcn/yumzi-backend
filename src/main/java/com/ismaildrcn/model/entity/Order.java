package com.ismaildrcn.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.ismaildrcn.model.enums.OrderDeliveryType;
import com.ismaildrcn.model.enums.OrderStatus;
import com.ismaildrcn.model.enums.PaymentMethod;
import com.ismaildrcn.model.enums.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity {

    @Column(name = "order_number", unique = true, nullable = false)
    private String orderNumber;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "delivery_note")
    private String deliveryNote;

    @Column(name = "estimated_delivery_time", nullable = false)
    private String estimatedDeliveryTime;

    @Column(name = "actual_delivery_time")
    private String actualDeliveryTime;

    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal;

    @Column(name = "delivery_fee", nullable = false)
    private BigDecimal deliveryFee;

    @Column(name = "tax_amount", nullable = false)
    private BigDecimal taxAmount;

    @Column(name = "discount_amount", nullable = true)
    private BigDecimal discountAmount;

    @Column(name = "coupon_code", nullable = true)
    private String couponCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_type", nullable = false)
    private OrderDeliveryType deliveryType;

    @Column(name = "service_fee", nullable = true)
    private BigDecimal serviceFee;

    @Column(name = "coupon_discount", nullable = true)
    private BigDecimal couponDiscount;

    @Column(name = "payment_id", nullable = true)
    private String paymentId;

    @Column(name = "customer_note", columnDefinition = "TEXT")
    private String customerNote;

    @Column(name = "restaurant_note", columnDefinition = "TEXT")
    private String restaurantNote;

    @Column(name = "cancellation_reason")
    private String cancellationReason;

    @Column(name = "cancelled_by")
    private String cancelledBy;

    @Column(name = "refund_amount")
    private BigDecimal refundAmount;

    @Column(name = "refund_status")
    private String refundStatus;

    @Column(name = "customer_rating")
    private Integer customerRating;

    @Column(name = "customer_review", columnDefinition = "TEXT")
    private String customerReview;

    @Column(name = "restaurant_rating")
    private Integer restaurantRating;

    @Column(name = "restaurant_review", columnDefinition = "TEXT")
    private String restaurantReview;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

}

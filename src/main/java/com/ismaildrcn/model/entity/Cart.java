package com.ismaildrcn.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Cart")
public class Cart implements Serializable {
    // Aktif kullanici sepeti (Redis'te geçici, 2 saat TTL)

    @Id
    private String id; // Format: "user:{userId}"

    @Indexed
    private UUID userId; // User'ın UUID'si

    private BigDecimal subTotal = BigDecimal.ZERO;
    private BigDecimal deliveryFee = BigDecimal.ZERO;
    private BigDecimal taxAmount = BigDecimal.ZERO;
    private BigDecimal serviceFee = BigDecimal.ZERO;
    private BigDecimal discountAmount = BigDecimal.ZERO;
    private BigDecimal totalAmount = BigDecimal.ZERO;

    private String couponCode;
    private BigDecimal couponDiscount = BigDecimal.ZERO;

    private Boolean isActive = true;
    private LocalDateTime expiresAt;
    private Boolean convertedToOrder = false;
    private LocalDateTime convertedAt;

    // CartItem'lar embedded olarak (ayrı tablo yok)
    private List<CartItem> cartItems = new ArrayList<>();

    private UUID restaurantId;

    @TimeToLive
    private Long ttl = 7200L; // 2 saat (otomatik expire)

}

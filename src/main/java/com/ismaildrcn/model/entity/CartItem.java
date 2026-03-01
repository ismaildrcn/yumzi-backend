package com.ismaildrcn.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem implements Serializable {
    // Sepetteki ürün (Redis'te Cart içinde embedded)
    // MenuItem bilgilerine ihtiyaç duyulduğunda menuItemId ile DB'den çekilir

    private UUID menuItemId; // MenuItem referansı (join yok, sadece ID)

    private Integer quantity = 1;

    // Snapshot: Sepete eklendiği andaki fiyatlar
    private BigDecimal unitPrice;
    private BigDecimal discountedUnitPrice;
    private BigDecimal totalPrice;

    private String specialInstructions;

}

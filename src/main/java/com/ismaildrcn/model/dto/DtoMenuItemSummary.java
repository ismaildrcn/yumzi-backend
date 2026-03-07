package com.ismaildrcn.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.ismaildrcn.model.enums.CurrencyType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoMenuItemSummary {

    private UUID uniqueId;

    private String name;

    private String description;

    private String slug;

    private BigDecimal price;

    private BigDecimal discountPrice;

    private double discountPercentage;

    private CurrencyType currency;

    private boolean isActive;

    private String imageUrl;

    private String thumbnailUrl;

}

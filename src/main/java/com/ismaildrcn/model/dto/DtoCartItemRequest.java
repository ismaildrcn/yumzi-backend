package com.ismaildrcn.model.dto;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCartItemRequest {

    @NotNull(message = "Menu item ID is required")
    private UUID menuItemId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity = 1;

    private String specialInstructions;

}

package com.ismaildrcn.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.ismaildrcn.model.embeddable.BankAccounts;
import com.ismaildrcn.model.embeddable.OpeningHours;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoRestaurantRequest {

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @Size(max = 250, message = "Description cannot exceed 250 characters")
    private String description;

    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number")
    private String phoneNumber;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String email;

    private String website;

    @NotNull(message = "Opening hours cannot be null")
    private OpeningHours openingHours;

    @NotNull(message = "Delivery time range cannot be null")
    private String deliveryTimeRange;

    @NotNull(message = "Minimum order amount cannot be null")
    private BigDecimal minimumOrderAmount = BigDecimal.ZERO;

    @NotNull(message = "Delivery fee cannot be null")
    private BigDecimal deliveryFee = BigDecimal.ZERO;

    private double deliveryRadiusKm = 5.0;

    private boolean isAcceptingOrders = false;

    @NotNull(message = "Logo URL cannot be null")
    private String logoUrl;

    @NotNull(message = "Cover image URL cannot be null")
    private String coverImageUrl;

    @NotNull(message = "Tax number cannot be null")
    private String taxNumber;

    @NotNull(message = "Bank account info cannot be null")
    private BankAccounts bankAccountInfo;

    // Iliskili alanlar
    private UUID cuisineId;

    private UUID categoryId;

}

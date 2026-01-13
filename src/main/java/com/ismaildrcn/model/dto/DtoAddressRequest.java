package com.ismaildrcn.model.dto;

import com.ismaildrcn.model.enums.AddressType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoAddressRequest {

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Address type cannot be null")
    private AddressType addressType;

    @NotNull(message = "Address line 1 cannot be null")
    @Size(min = 5, max = 255, message = "Address line 1 must be between 5 and 255 characters")
    private String addressLine1;

    private String addressLine2;

    @NotNull(message = "District cannot be null")
    private String district;

    @NotNull(message = "City cannot be null")
    private String city;

    @NotNull(message = "Postal code cannot be null")
    private String state;

    @NotNull(message = "Country cannot be null")
    private String country;

    @NotNull(message = "Recipient name cannot be null")
    private String recipientName;

    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number")
    private String phoneNumber;

    @Pattern(regexp = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$\r\n", message = "Invalid latitude format")
    private String latitude;

    @Pattern(regexp = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$\r\n", message = "Invalid longitude format")
    private String longitude;

    @NotNull(message = "isDefault cannot be null")
    private boolean isDefault;

}

package com.ismaildrcn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private Long id;

    private Long userId;

    private String title;

    private String addressType;

    private String addressLine1;

    private String addressLine2;

    private String district;

    private String city;

    private String state;

    private String country;

    private String recipientName;

    private String phoneNumber;

    private String latitude;

    private String longitude;

    private boolean isDefault;

}

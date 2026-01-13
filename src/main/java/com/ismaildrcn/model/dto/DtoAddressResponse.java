package com.ismaildrcn.model.dto;

import java.util.UUID;

import com.ismaildrcn.model.enums.AddressType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoAddressResponse {

    private UUID addressUniqueId;

    private String title;

    private AddressType addressType;

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

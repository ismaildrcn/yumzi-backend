package com.ismaildrcn.model.dto;

import java.util.UUID;

import com.ismaildrcn.model.embeddable.OpeningHours;
import com.ismaildrcn.model.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoRestaurantSummary {

    private UUID uniqueId;

    private String name;

    private String slug;

    private OpeningHours openingHours;

    private String deliveryTimeRange;

    private Address address;

    private double rating;

    private String coverImageUrl;

}

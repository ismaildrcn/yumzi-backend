package com.ismaildrcn.model.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoRestaurantCuisineResponse {

    private UUID uniqueId;

    private String name;

    private String slug;

    private String description;

    private String iconUrl;

    private Integer sortOrder;

}

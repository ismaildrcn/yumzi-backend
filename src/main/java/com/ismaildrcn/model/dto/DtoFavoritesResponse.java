package com.ismaildrcn.model.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoFavoritesResponse {

    private Set<DtoMenuItemSummary> favoriteItems = new HashSet<>();

    private Set<DtoRestaurantSummary> favoriteRestaurants = new HashSet<>();

}

package com.ismaildrcn.service;

import java.util.UUID;

import com.ismaildrcn.model.dto.DtoFavoritesResponse;
import com.ismaildrcn.model.entity.User;

public interface IUserFavoriteService {

    void addFavoriteItem(User user, UUID menuItemId);

    void removeFavoriteItem(User user, UUID menuItemId);

    void addFavoriteRestaurant(User user, UUID restaurantId);

    void removeFavoriteRestaurant(User user, UUID restaurantId);

    DtoFavoritesResponse getFavorites(User user);

}

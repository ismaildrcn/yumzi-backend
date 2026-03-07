package com.ismaildrcn.controller;

import java.util.UUID;

import com.ismaildrcn.model.entity.User;

public interface IRestUserFavoriteController {

    RootEntity<?> addFavoriteItem(User user, UUID menuItemId);

    RootEntity<?> removeFavoriteItem(User user, UUID menuItemId);

    RootEntity<?> addFavoriteRestaurant(User user, UUID restaurantId);

    RootEntity<?> removeFavoriteRestaurant(User user, UUID restaurantId);

    RootEntity<?> getFavorites(User user);
}

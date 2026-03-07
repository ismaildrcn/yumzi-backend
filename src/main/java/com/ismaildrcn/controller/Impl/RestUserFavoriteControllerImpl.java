package com.ismaildrcn.controller.Impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildrcn.controller.IRestUserFavoriteController;
import com.ismaildrcn.controller.RestBaseController;
import com.ismaildrcn.controller.RootEntity;
import com.ismaildrcn.model.entity.User;
import com.ismaildrcn.service.IUserFavoriteService;

@RestController
@RequestMapping("/rest/api/user/favorites")
public class RestUserFavoriteControllerImpl extends RestBaseController implements IRestUserFavoriteController {

    @Autowired
    IUserFavoriteService favoriteService;

    @Override
    @PostMapping("/item/{menuItemId}")
    public RootEntity<?> addFavoriteItem(@AuthenticationPrincipal User user, @PathVariable UUID menuItemId) {
        favoriteService.addFavoriteItem(user, menuItemId);
        return ok("Item added to favorites successfully.");
    }

    @Override
    @PostMapping("/restaurant/{restaurantId}")
    public RootEntity<?> addFavoriteRestaurant(@AuthenticationPrincipal User user, @PathVariable UUID restaurantId) {
        favoriteService.addFavoriteRestaurant(user, restaurantId);
        return ok("Restaurant added to favorites successfully.");
    }

    @Override
    @DeleteMapping("/item/{menuItemId}")
    public RootEntity<?> removeFavoriteItem(@AuthenticationPrincipal User user, @PathVariable UUID menuItemId) {
        favoriteService.removeFavoriteItem(user, menuItemId);
        return ok("Item removed from favorites successfully.");
    }

    @Override
    @DeleteMapping("/restaurant/{restaurantId}")
    public RootEntity<?> removeFavoriteRestaurant(@AuthenticationPrincipal User user, @PathVariable UUID restaurantId) {
        favoriteService.removeFavoriteRestaurant(user, restaurantId);
        return ok("Restaurant removed from favorites successfully.");
    }

    @Override
    @GetMapping("")
    public RootEntity<?> getFavorites(@AuthenticationPrincipal User user) {
        return ok(favoriteService.getFavorites(user));
    }

}

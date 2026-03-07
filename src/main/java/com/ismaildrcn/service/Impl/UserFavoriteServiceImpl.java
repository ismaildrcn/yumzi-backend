package com.ismaildrcn.service.Impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ismaildrcn.exception.BaseException;
import com.ismaildrcn.exception.ErrorMessage;
import com.ismaildrcn.exception.MessageType;
import com.ismaildrcn.model.dto.DtoFavoritesResponse;
import com.ismaildrcn.model.dto.DtoMenuItemSummary;
import com.ismaildrcn.model.dto.DtoRestaurantSummary;
import com.ismaildrcn.model.entity.MenuItem;
import com.ismaildrcn.model.entity.Restaurant;
import com.ismaildrcn.model.entity.User;
import com.ismaildrcn.repository.MenuItemRepository;
import com.ismaildrcn.repository.RestaurantRepository;
import com.ismaildrcn.repository.UserRepository;
import com.ismaildrcn.service.IUserFavoriteService;

@Service
public class UserFavoriteServiceImpl implements IUserFavoriteService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MenuItemRepository menuItemRepo;

    @Autowired
    private RestaurantRepository restaurantRepo;

    @Override
    @Transactional
    public void addFavoriteItem(User user, UUID menuItemId) {

        User userById = getUserFromDatabase(user);

        Optional<MenuItem> menuItem = menuItemRepo.findByUniqueId(menuItemId);
        if (menuItem.isPresent()) {
            userById.getFavoriteItems().add(menuItem.get());
            userRepo.save(userById);
        }
    }

    @Override
    @Transactional
    public void addFavoriteRestaurant(User user, UUID restaurantId) {
        User userById = getUserFromDatabase(user);
        Optional<Restaurant> restaurant = restaurantRepo.findByUniqueId(restaurantId);
        if (restaurant.isPresent()) {
            userById.getFavoriteRestaurants().add(restaurant.get());
            userRepo.save(userById);
        }

    }

    @Override
    @Transactional
    public void removeFavoriteItem(User user, UUID menuItemId) {
        User userById = getUserFromDatabase(user);
        Optional<MenuItem> menuItem = menuItemRepo.findByUniqueId(menuItemId);
        if (menuItem.isPresent()) {
            userById.getFavoriteItems().remove(menuItem.get());
            userRepo.save(userById);
        }
    }

    @Override
    @Transactional
    public void removeFavoriteRestaurant(User user, UUID restaurantId) {
        User userById = getUserFromDatabase(user);
        Optional<Restaurant> restaurant = restaurantRepo.findByUniqueId(restaurantId);
        if (restaurant.isPresent()) {
            userById.getFavoriteRestaurants().remove(restaurant.get());
            userRepo.save(userById);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public DtoFavoritesResponse getFavorites(User user) {
        User userEntity = userRepo.findUserWithFavorites(user.getId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_FOUND, user.getId().toString())));

        DtoFavoritesResponse response = new DtoFavoritesResponse();

        userEntity.getFavoriteItems().forEach(menuItem -> {
            DtoMenuItemSummary menuItemResponse = new DtoMenuItemSummary();
            BeanUtils.copyProperties(menuItem, menuItemResponse);
            menuItemResponse.setFavorite(true);
            response.getFavoriteItems().add(menuItemResponse);
        });

        userEntity.getFavoriteRestaurants().forEach(restaurant -> {
            DtoRestaurantSummary restaurantResponse = new DtoRestaurantSummary();
            BeanUtils.copyProperties(restaurant, restaurantResponse);
            restaurantResponse.setFavorite(true);
            response.getFavoriteRestaurants().add(restaurantResponse);
        });

        return response;
    }

    private User getUserFromDatabase(User user) {
        return userRepo.findById(user.getId()).orElseThrow(() -> new BaseException(
                new ErrorMessage(
                        MessageType.NO_RECORD_FOUND,
                        user.getId().toString())));
    }

}

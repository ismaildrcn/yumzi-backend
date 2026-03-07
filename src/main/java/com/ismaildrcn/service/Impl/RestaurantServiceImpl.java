package com.ismaildrcn.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildrcn.exception.BaseException;
import com.ismaildrcn.exception.ErrorMessage;
import com.ismaildrcn.exception.MessageType;
import com.ismaildrcn.model.dto.DtoMenuCategoryResponse;
import com.ismaildrcn.model.dto.DtoRestaurantCategoryResponse;
import com.ismaildrcn.model.dto.DtoRestaurantCuisineResponse;
import com.ismaildrcn.model.dto.DtoRestaurantRequest;
import com.ismaildrcn.model.dto.DtoRestaurantResponse;
import com.ismaildrcn.model.dto.DtoRestaurantSummary;
import com.ismaildrcn.model.entity.MenuCategory;
import com.ismaildrcn.model.entity.Restaurant;
import com.ismaildrcn.model.entity.RestaurantCategory;
import com.ismaildrcn.model.entity.RestaurantCuisine;
import com.ismaildrcn.model.entity.User;
import com.ismaildrcn.repository.RestaurantCategoryRepository;
import com.ismaildrcn.repository.RestaurantCuisineRepository;
import com.ismaildrcn.repository.RestaurantRepository;
import com.ismaildrcn.repository.UserRepository;
import com.ismaildrcn.service.IRestaurantService;
import com.ismaildrcn.utils.SlugUtils;

@Service
public class RestaurantServiceImpl implements IRestaurantService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantCategoryRepository restaurantCategoryRepository;

    @Autowired
    private RestaurantCuisineRepository restaurantCuisineRepository;

    @Override
    public List<DtoRestaurantSummary> findRestaurantsByCategoryId(User user, UUID categoryId) {
        Set<UUID> favoriteRestaurantIds = new HashSet<>();
        if (user != null) {
            favoriteRestaurantIds = userRepo.findFavoriteRestaurantIds(user.getId());
        }

        List<Restaurant> dbRestaurants = restaurantRepository.findRestaurantByCategoryId(categoryId).stream()
                .filter(restaurant -> restaurant.getDeletedAt() == null).toList();
        List<DtoRestaurantSummary> response = new ArrayList<>();
        for (Restaurant restaurant : dbRestaurants) {
            DtoRestaurantSummary summary = new DtoRestaurantSummary();
            BeanUtils.copyProperties(restaurant, summary);
            if (favoriteRestaurantIds.contains(restaurant.getUniqueId())) {
                summary.setFavorite(true);
            }
            response.add(summary);
        }
        return response;
    }

    @Override
    public void deleteRestaurantByUniqueId(UUID uniqueId) {
        Restaurant restaurantEntity = restaurantRepository.findByUniqueId(uniqueId).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND,
                        "Restaurant with uniqueId " + uniqueId + " not found.")));

        restaurantEntity.setDeletedAt(LocalDateTime.now());
        restaurantRepository.save(restaurantEntity);
    }

    @Override
    public DtoRestaurantResponse getRestaurantByUniqueId(User user, UUID uniqueId) {
        Restaurant restaurantEntity = restaurantRepository.findByUniqueId(uniqueId).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND,
                        "Restaurant with uniqueId " + uniqueId + " not found.")));

        Set<UUID> favoriteRestaurantIds = new HashSet<>();
        if (user != null) {
            favoriteRestaurantIds = userRepo.findFavoriteRestaurantIds(user.getId());
        }
        DtoRestaurantResponse response = convertToDto(restaurantEntity);
        if (favoriteRestaurantIds.contains(restaurantEntity.getUniqueId())) {
            response.setFavorite(true);
        }
        return response;
    }

    @Override
    public DtoRestaurantResponse saveRestaurant(DtoRestaurantRequest request) {
        Restaurant restaurantEntity = createRestaurantEntity(request);
        restaurantEntity = restaurantRepository.save(restaurantEntity);

        DtoRestaurantResponse response = convertToDto(restaurantEntity);
        return response;
    }

    @Override
    public DtoRestaurantResponse updateRestaurantByUniqueId(UUID uniqueId, DtoRestaurantRequest request) {
        checkExistsForUpdate(request, uniqueId);
        Restaurant dbRestaurant = restaurantRepository.findByUniqueId(uniqueId)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND,
                        "Restaurant with uniqueId " + uniqueId + " not found.")));

        BeanUtils.copyProperties(request, dbRestaurant);
        restaurantRepository.save(dbRestaurant);

        DtoRestaurantResponse response = convertToDto(dbRestaurant);
        return response;
    }

    @Override
    public List<DtoRestaurantSummary> getAllRestaurants(User user) {
        Set<UUID> favoriteRestaurantIds = new HashSet<>();
        if (user != null) {
            favoriteRestaurantIds = userRepo.findFavoriteRestaurantIds(user.getId());
        }

        List<Restaurant> dbRestaurants = restaurantRepository.findAll().stream()
                .filter(restaurant -> restaurant.getDeletedAt() == null).toList();

        List<DtoRestaurantSummary> response = new ArrayList<>();

        for (Restaurant restaurant : dbRestaurants) {
            DtoRestaurantSummary summary = new DtoRestaurantSummary();
            BeanUtils.copyProperties(restaurant, summary);

            if (favoriteRestaurantIds.contains(restaurant.getUniqueId())) {
                summary.setFavorite(true);
            }
            response.add(summary);
        }
        return response;
    }

    private DtoRestaurantResponse convertToDto(Restaurant restaurant) {
        DtoRestaurantResponse response = new DtoRestaurantResponse();
        DtoRestaurantCategoryResponse categoryResponse = new DtoRestaurantCategoryResponse();
        DtoRestaurantCuisineResponse cuisineResponse = new DtoRestaurantCuisineResponse();
        List<DtoMenuCategoryResponse> menuCategoryResponses = new ArrayList<>();

        for (MenuCategory menuCategory : restaurant.getMenuCategories()) {
            DtoMenuCategoryResponse menuCategoryResponse = new DtoMenuCategoryResponse();
            BeanUtils.copyProperties(menuCategory, menuCategoryResponse);
            menuCategoryResponses.add(menuCategoryResponse);
        }
        BeanUtils.copyProperties(restaurant, response);
        BeanUtils.copyProperties(restaurant.getCategory(), categoryResponse);
        BeanUtils.copyProperties(restaurant.getCuisine(), cuisineResponse);
        response.setCategory(categoryResponse);
        response.setCuisine(cuisineResponse);
        response.setMenuCategories(menuCategoryResponses);
        return response;
    }

    private Restaurant createRestaurantEntity(DtoRestaurantRequest request) {
        isExistsRestaurant(request);
        Restaurant restaurant = new Restaurant();

        BeanUtils.copyProperties(request, restaurant);

        RestaurantCategory dbCategory = restaurantCategoryRepository.findByUniqueId(request.getCategoryId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND,
                        "Restaurant Category with uniqueId " + request.getCategoryId() + " not found.")));

        RestaurantCuisine dbCuisine = restaurantCuisineRepository.findByUniqueId(request.getCuisineId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND,
                        "Restaurant Cuisine with uniqueId " + request.getCuisineId() + " not found.")));

        restaurant.setCategory(dbCategory);
        restaurant.setCuisine(dbCuisine);

        return restaurant;
    }

    private void isExistsRestaurant(DtoRestaurantRequest request) {
        String slug = SlugUtils.generateSlug(request.getName());

        if (restaurantRepository.existsByName(request.getName())) {
            throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD,
                    "Restaurant with name " + request.getName() + " already exists."));
        }

        if (restaurantRepository.existsBySlug(slug)) {
            throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD,
                    "Restaurant with slug " + slug + " already exists."));
        }
    }

    private void checkExistsForUpdate(DtoRestaurantRequest request, UUID currentId) {
        String slug = SlugUtils.generateSlug(request.getName());

        // Slug kontrolü - kendi kaydı hariç
        restaurantRepository.findBySlug(slug).ifPresent(restaurant -> {
            if (!restaurant.getUniqueId().equals(currentId)) {
                throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD, "Slug: " + slug));
            }
        });

        // Name kontrolü - kendi kaydı hariç
        restaurantRepository.findByName(request.getName()).ifPresent(restaurant -> {
            if (!restaurant.getUniqueId().equals(currentId)) {
                throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD,
                        "Name: " + request.getName()));
            }
        });
    }

}

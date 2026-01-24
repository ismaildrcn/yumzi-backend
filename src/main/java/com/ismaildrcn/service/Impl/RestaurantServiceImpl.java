package com.ismaildrcn.service.Impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildrcn.exception.BaseException;
import com.ismaildrcn.exception.ErrorMessage;
import com.ismaildrcn.exception.MessageType;
import com.ismaildrcn.model.dto.DtoRestaurantCategoryResponse;
import com.ismaildrcn.model.dto.DtoRestaurantCuisineResponse;
import com.ismaildrcn.model.dto.DtoRestaurantRequest;
import com.ismaildrcn.model.dto.DtoRestaurantResponse;
import com.ismaildrcn.model.entity.Restaurant;
import com.ismaildrcn.model.entity.RestaurantCategory;
import com.ismaildrcn.model.entity.RestaurantCuisine;
import com.ismaildrcn.repository.RestaurantCategoryRepository;
import com.ismaildrcn.repository.RestaurantCuisineRepository;
import com.ismaildrcn.repository.RestaurantRepository;
import com.ismaildrcn.service.IRestaurantService;
import com.ismaildrcn.utils.SlugUtils;

@Service
public class RestaurantServiceImpl implements IRestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantCategoryRepository restaurantCategoryRepository;

    @Autowired
    private RestaurantCuisineRepository restaurantCuisineRepository;

    @Override
    public void deleteRestaurantByUniqueId(UUID uniqueId) {
        Restaurant restaurantEntity = restaurantRepository.findByUniqueId(uniqueId).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND,
                        "Restaurant with uniqueId " + uniqueId + " not found.")));

        restaurantEntity.setDeletedAt(LocalDateTime.now());
        restaurantRepository.save(restaurantEntity);
    }

    @Override
    public DtoRestaurantResponse getRestaurantByUniqueId(UUID uniqueId) {
        Restaurant restaurantEntity = restaurantRepository.findByUniqueId(uniqueId).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND,
                        "Restaurant with uniqueId " + uniqueId + " not found.")));
        DtoRestaurantResponse response = convertToDto(restaurantEntity);
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

    private DtoRestaurantResponse convertToDto(Restaurant restaurant) {
        DtoRestaurantResponse response = new DtoRestaurantResponse();
        DtoRestaurantCategoryResponse categoryResponse = new DtoRestaurantCategoryResponse();
        DtoRestaurantCuisineResponse cuisineResponse = new DtoRestaurantCuisineResponse();

        BeanUtils.copyProperties(restaurant, response);
        BeanUtils.copyProperties(restaurant.getCategory(), categoryResponse);
        BeanUtils.copyProperties(restaurant.getCuisine(), cuisineResponse);
        response.setCategory(categoryResponse);
        response.setCuisine(cuisineResponse);
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

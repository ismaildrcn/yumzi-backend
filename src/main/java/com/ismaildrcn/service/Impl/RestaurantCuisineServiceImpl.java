package com.ismaildrcn.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ismaildrcn.exception.BaseException;
import com.ismaildrcn.exception.ErrorMessage;
import com.ismaildrcn.exception.MessageType;
import com.ismaildrcn.model.dto.DtoRestaurantCuisineRequest;
import com.ismaildrcn.model.dto.DtoRestaurantCuisineResponse;
import com.ismaildrcn.model.entity.RestaurantCuisine;
import com.ismaildrcn.repository.RestaurantCuisineRepository;
import com.ismaildrcn.service.IRestaurantCuisineService;
import com.ismaildrcn.utils.SlugUtils;

@Service
public class RestaurantCuisineServiceImpl implements IRestaurantCuisineService {

    @Autowired
    private RestaurantCuisineRepository restaurantCuisineRepository;

    @Override
    public List<DtoRestaurantCuisineResponse> getAllCuisines(boolean isAsc) {
        List<DtoRestaurantCuisineResponse> response = new ArrayList<>();
        Sort sort = isAsc ? Sort.by("sortOrder").ascending() : Sort.by("sortOrder").descending();
        List<RestaurantCuisine> dbCuisines = restaurantCuisineRepository.findAll(sort);
        for (RestaurantCuisine cuisine : dbCuisines) {
            DtoRestaurantCuisineResponse dto = new DtoRestaurantCuisineResponse();
            BeanUtils.copyProperties(cuisine, dto);
            response.add(dto);
        }
        return response;
    }

    @Override
    public DtoRestaurantCuisineResponse saveRestaurantCuisine(DtoRestaurantCuisineRequest request) {
        RestaurantCuisine cuisine = createRestaurantCuisineEntity(request);

        DtoRestaurantCuisineResponse response = new DtoRestaurantCuisineResponse();
        BeanUtils.copyProperties(cuisine, response);
        return response;
    }

    @Override
    public DtoRestaurantCuisineResponse updateRestaurantCuisine(UUID uniqueId, DtoRestaurantCuisineRequest request) {
        checkExistsForUpdate(request, uniqueId);
        RestaurantCuisine cuisine = restaurantCuisineRepository.findByUniqueId(uniqueId)
                .orElseThrow(
                        () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND, "ID: " + uniqueId)));

        BeanUtils.copyProperties(request, cuisine);
        cuisine.setSlug(SlugUtils.generateSlug(request.getName()));
        restaurantCuisineRepository.save(cuisine);

        DtoRestaurantCuisineResponse response = new DtoRestaurantCuisineResponse();
        BeanUtils.copyProperties(cuisine, response);
        return response;
    }

    private RestaurantCuisine createRestaurantCuisineEntity(DtoRestaurantCuisineRequest request) {
        isExistsRecord(request);
        RestaurantCuisine cuisine = new RestaurantCuisine();
        BeanUtils.copyProperties(request, cuisine);

        if (cuisine.getSortOrder() == null) {
            Integer maxSortOrder = restaurantCuisineRepository.findMaxSortOrder();
            cuisine.setSortOrder((maxSortOrder == null ? 0 : maxSortOrder) + 1);
        }

        cuisine = restaurantCuisineRepository.save(cuisine);

        return cuisine;
    }

    private void isExistsRecord(DtoRestaurantCuisineRequest request) {
        String slug = SlugUtils.generateSlug(request.getName());

        if (restaurantCuisineRepository.existsBySlug(slug)) {
            throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD, "Slug: " + slug));
        }

        if (restaurantCuisineRepository.existsByName(request.getName())) {
            throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD,
                    "Name: " + request.getName()));
        }
    }

    // Yeni metod: Update için kontrol (kendi ID'si hariç)
    private void checkExistsForUpdate(DtoRestaurantCuisineRequest request, UUID currentId) {
        String slug = SlugUtils.generateSlug(request.getName());

        // Slug kontrolü - kendi kaydı hariç
        restaurantCuisineRepository.findBySlug(slug).ifPresent(cuisine -> {
            if (!cuisine.getUniqueId().equals(currentId)) {
                throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD, "Slug: " + slug));
            }
        });

        // Name kontrolü - kendi kaydı hariç
        restaurantCuisineRepository.findByName(request.getName()).ifPresent(cuisine -> {
            if (!cuisine.getUniqueId().equals(currentId)) {
                throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD,
                        "Name: " + request.getName()));
            }
        });
    }

}

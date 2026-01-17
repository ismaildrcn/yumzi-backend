package com.ismaildrcn.service.Impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ismaildrcn.exception.BaseException;
import com.ismaildrcn.exception.ErrorMessage;
import com.ismaildrcn.exception.MessageType;
import com.ismaildrcn.model.dto.DtoRestaurantCategoryRequest;
import com.ismaildrcn.model.dto.DtoRestaurantCategoryResponse;
import com.ismaildrcn.model.entity.RestaurantCategory;
import com.ismaildrcn.repository.RestaurantCategoryRepository;
import com.ismaildrcn.service.IRestaurantCategoryService;
import com.ismaildrcn.utils.SlugUtils;

@Service
public class IRestaurantCategoryServiceImpl implements IRestaurantCategoryService {

    @Autowired
    RestaurantCategoryRepository restaurantCategoryRepository;

    @Override
    public DtoRestaurantCategoryResponse saveRestaurantCategory(
            DtoRestaurantCategoryRequest restaurantCategoryRequest) {
        RestaurantCategory restaurantCategory = createRestaurantCategoryFromDto(restaurantCategoryRequest);

        DtoRestaurantCategoryResponse dtoRestaurantCategoryResponse = new DtoRestaurantCategoryResponse();
        BeanUtils.copyProperties(restaurantCategory, dtoRestaurantCategoryResponse);

        return dtoRestaurantCategoryResponse;
    }

    @Override
    public List<DtoRestaurantCategoryResponse> getAllRestaurantCategories(Boolean isAsc) {
        List<DtoRestaurantCategoryResponse> response = new java.util.ArrayList<>();
        Sort sort = isAsc ? Sort.by("sortOrder").ascending() : Sort.by("sortOrder").descending();
        List<RestaurantCategory> categories = restaurantCategoryRepository.findAll(sort);

        for (RestaurantCategory category : categories) {
            DtoRestaurantCategoryResponse dto = new DtoRestaurantCategoryResponse();
            BeanUtils.copyProperties(category, dto);
            response.add(dto);
        }
        return response;
    }

    @Override
    public DtoRestaurantCategoryResponse updateRestaurantCategory(UUID uniqueId,
            DtoRestaurantCategoryRequest request) {
        RestaurantCategory dbCategory = restaurantCategoryRepository.findByUniqueId(uniqueId).orElseThrow(() -> {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND,
                    "Restaurant Category not found with uniqueId: " + uniqueId));
        });

        checkExistsForUpdate(request, uniqueId);

        BeanUtils.copyProperties(request, dbCategory);
        dbCategory = restaurantCategoryRepository.save(dbCategory);

        DtoRestaurantCategoryResponse dtoRestaurantCategoryResponse = new DtoRestaurantCategoryResponse();
        BeanUtils.copyProperties(dbCategory, dtoRestaurantCategoryResponse);

        return dtoRestaurantCategoryResponse;
    }

    private RestaurantCategory createRestaurantCategoryFromDto(
            DtoRestaurantCategoryRequest request) {
        isExistsRecord(request);

        RestaurantCategory restaurantCategory = new RestaurantCategory();
        BeanUtils.copyProperties(request, restaurantCategory);
        if (restaurantCategory.getSortOrder() == null) {
            Integer maxSortOrder = restaurantCategoryRepository.findMaxSortOrder();
            restaurantCategory.setSortOrder((maxSortOrder == null ? 0 : maxSortOrder) + 1);
        }

        restaurantCategory = restaurantCategoryRepository.save(restaurantCategory);
        return restaurantCategory;
    }

    private void isExistsRecord(DtoRestaurantCategoryRequest request) {
        String slug = SlugUtils.generateSlug(request.getName());

        if (restaurantCategoryRepository.existsBySlug(slug)) {
            throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD, "Slug: " + slug));
        }

        if (restaurantCategoryRepository.existsByName(request.getName())) {
            throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD,
                    "Name: " + request.getName()));
        }
    }

    // Yeni metod: Update için kontrol (kendi ID'si hariç)
    private void checkExistsForUpdate(DtoRestaurantCategoryRequest request, UUID currentId) {
        String slug = SlugUtils.generateSlug(request.getName());

        // Slug kontrolü - kendi kaydı hariç
        restaurantCategoryRepository.findBySlug(slug).ifPresent(category -> {
            if (!category.getUniqueId().equals(currentId)) {
                throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD, "Slug: " + slug));
            }
        });

        // Name kontrolü - kendi kaydı hariç
        restaurantCategoryRepository.findByName(request.getName()).ifPresent(category -> {
            if (!category.getUniqueId().equals(currentId)) {
                throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD,
                        "Name: " + request.getName()));
            }
        });
    }

}

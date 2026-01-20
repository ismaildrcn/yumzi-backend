package com.ismaildrcn.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildrcn.exception.BaseException;
import com.ismaildrcn.exception.ErrorMessage;
import com.ismaildrcn.exception.MessageType;
import com.ismaildrcn.model.dto.DtoMenuCategoryResponse;
import com.ismaildrcn.model.dto.DtoMenuItemRequest;
import com.ismaildrcn.model.dto.DtoMenuItemResponse;
import com.ismaildrcn.model.dto.DtoRestaurantSummary;
import com.ismaildrcn.model.entity.MenuCategory;
import com.ismaildrcn.model.entity.MenuItem;
import com.ismaildrcn.model.entity.Restaurant;
import com.ismaildrcn.repository.MenuCategoryRepository;
import com.ismaildrcn.repository.MenuItemRepository;
import com.ismaildrcn.repository.RestaurantRepository;
import com.ismaildrcn.service.IMenuItemService;
import com.ismaildrcn.utils.SlugUtils;

@Service
public class MenuItemServiceImpl implements IMenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    @Override
    public DtoMenuItemResponse saveMenuItem(UUID restaurantUniqueId, DtoMenuItemRequest request) {
        MenuItem menuItemEntity = createMenuItemFromRequest(restaurantUniqueId, request);
        menuItemEntity = menuItemRepository.save(menuItemEntity);
        DtoMenuItemResponse response = convertToDto(menuItemEntity);
        return response;
    }

    @Override
    public List<DtoMenuItemResponse> findMenuItemsByRestaurantId(UUID restaurantUniqueId) {
        List<DtoMenuItemResponse> responseList = new ArrayList<>();
        List<MenuItem> menuItemListByRestaurant = menuItemRepository.findByRestaurantUniqueId(restaurantUniqueId);

        for (MenuItem menuItem : menuItemListByRestaurant) {
            DtoMenuItemResponse dtoMenuItemResponse = convertToDto(menuItem);
            responseList.add(dtoMenuItemResponse);
        }
        return responseList;
    }

    @Override
    public DtoMenuItemResponse updateMenuItem(UUID restaurantId, UUID menuItemId, DtoMenuItemRequest request) {
        checkExistsForUpdate(request, restaurantId, menuItemId);

        MenuItem dbMenuItem = menuItemRepository.findByUniqueId(menuItemId)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND,
                        "Menu item with uniqueId: " + menuItemId + " not found.")));

        BeanUtils.copyProperties(request, dbMenuItem);

        MenuItem updatedMenuItem = menuItemRepository.save(dbMenuItem);

        DtoMenuItemResponse response = convertToDto(updatedMenuItem);
        return response;
    }

    @Override
    public void deleteMenuItem(UUID restaurantId, UUID menuItemId) {
        MenuItem menuItem = menuItemRepository.findByUniqueId(menuItemId)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND,
                        "Menu item with uniqueId: " + menuItemId + " not found.")));

        menuItem.setDeletedAt(LocalDateTime.now());
        menuItemRepository.save(menuItem);
        
    }

    private MenuItem createMenuItemFromRequest(UUID restaurantId, DtoMenuItemRequest request) {
        Restaurant restaurant = restaurantRepository.findByUniqueId(restaurantId)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND,
                        "Restaurant with uniqueId: " + restaurantId + " not found.")));

        isExistMenuItem(restaurantId, request);
        MenuItem menuItem = new MenuItem();
        BeanUtils.copyProperties(request, menuItem);

        MenuCategory menuCategory = menuCategoryRepository.findByUniqueId(request.getCategoryId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND,
                        "Menu category with uniqueId: " + request.getCategoryId() + " not found.")));

        menuItem.setRestaurant(restaurant);
        menuItem.setCategory(menuCategory);
        menuItem.setPrice(menuItem.getFinalPrice());

        return menuItem;
    }

    private void isExistMenuItem(UUID restaurantId, DtoMenuItemRequest request) {
        String slug = SlugUtils.generateSlug(request.getName());

        menuItemRepository.findByName(request.getName()).ifPresent(item -> {
            if (item.getRestaurant().getUniqueId().equals(restaurantId)) {
                throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD,
                        "Menu item with name '" + request.getName() + "' already exists."));
            }
        });

        menuItemRepository.findBySlug(slug).ifPresent(item -> {
            if (item.getRestaurant().getUniqueId().equals(restaurantId)) {
                throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD,
                        "Menu item with slug '" + slug + "' already exists."));
            }
        });
    }

    private void checkExistsForUpdate(DtoMenuItemRequest request, UUID restaurantId, UUID currentId) {
        String slug = SlugUtils.generateSlug(request.getName());

        // Slug kontrolü - kendi kaydı hariç
        menuItemRepository.findBySlug(slug).ifPresent(item -> {
            if (item.getRestaurant().getUniqueId().equals(restaurantId)
                    && !item.getUniqueId().equals(currentId)) {
                throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD, "Slug: " + slug));
            }
        });

        // Name kontrolü - kendi kaydı hariç
        menuItemRepository.findByName(request.getName()).ifPresent(item -> {
            if (item.getRestaurant().getUniqueId().equals(restaurantId)
                    && !item.getUniqueId().equals(currentId)) {
                throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD,
                        "Name: " + request.getName()));
            }
        });
    }

    private DtoMenuItemResponse convertToDto(MenuItem menuItemEntity) {
        DtoMenuItemResponse response = new DtoMenuItemResponse();
        DtoRestaurantSummary restaurantResponse = new DtoRestaurantSummary();
        DtoMenuCategoryResponse categoryResponse = new DtoMenuCategoryResponse();

        BeanUtils.copyProperties(menuItemEntity, response);
        BeanUtils.copyProperties(menuItemEntity.getRestaurant(), restaurantResponse);
        BeanUtils.copyProperties(menuItemEntity.getCategory(), categoryResponse);
        response.setRestaurant(restaurantResponse);
        response.setCategory(categoryResponse);
        return response;
    }
}

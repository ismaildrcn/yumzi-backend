package com.ismaildrcn.service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildrcn.exception.BaseException;
import com.ismaildrcn.exception.ErrorMessage;
import com.ismaildrcn.exception.MessageType;
import com.ismaildrcn.model.dto.DtoMenuItemRequest;
import com.ismaildrcn.model.dto.DtoMenuItemResponse;
import com.ismaildrcn.model.dto.DtoRestaurantResponse;
import com.ismaildrcn.model.entity.MenuItem;
import com.ismaildrcn.model.entity.Restaurant;
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

    @Override
    public DtoMenuItemResponse saveMenuItem(DtoMenuItemRequest request) {
        MenuItem menuItemEntity = createMenuItemFromRequest(request);
        menuItemEntity = menuItemRepository.save(menuItemEntity);
        DtoMenuItemResponse response = convertToDto(menuItemEntity);
        return response;
    }

    private MenuItem createMenuItemFromRequest(DtoMenuItemRequest request) {
        isExistMenuItem(request);

        MenuItem menuItem = new MenuItem();
        BeanUtils.copyProperties(request, menuItem);

        Restaurant restaurant = restaurantRepository.findByUniqueId(request.getRestaurantId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND,
                        "Restaurant with uniqueId: " + request.getRestaurantId() + " not found.")));

        menuItem.setRestaurant(restaurant);
        menuItem.setPrice(menuItem.getFinalPrice());

        return menuItem;
    }

    private void isExistMenuItem(DtoMenuItemRequest request) {
        String slug = SlugUtils.generateSlug(request.getName());

        if (menuItemRepository.existsByName(request.getName())) {
            throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD,
                    "Menu item with name '" + request.getName() + "' already exists."));
        }
        if (menuItemRepository.existsBySlug(slug)) {
            throw new BaseException(new ErrorMessage(MessageType.ALREADY_EXISTS_RECORD,
                    "Menu item with slug '" + slug + "' already exists."));
        }
    }

    private DtoMenuItemResponse convertToDto(MenuItem menuItemEntity) {
        DtoMenuItemResponse response = new DtoMenuItemResponse();
        DtoRestaurantResponse restaurantResponse = new DtoRestaurantResponse();

        BeanUtils.copyProperties(menuItemEntity, response);
        BeanUtils.copyProperties(menuItemEntity.getRestaurant(), restaurantResponse);
        response.setRestaurant(restaurantResponse);
        return response;
    }
}

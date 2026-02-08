package com.ismaildrcn.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildrcn.model.dto.DtoMenuItemResponse;
import com.ismaildrcn.model.dto.DtoRestaurantSummary;
import com.ismaildrcn.model.dto.DtoSearchResponse;
import com.ismaildrcn.model.entity.MenuItem;
import com.ismaildrcn.model.entity.Restaurant;
import com.ismaildrcn.repository.MenuItemRepository;
import com.ismaildrcn.repository.RestaurantRepository;
import com.ismaildrcn.service.ISearchService;

@Service
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private RestaurantRepository restRepo;

    @Autowired
    private MenuItemRepository menuItemRepo;

    @Override
    public DtoSearchResponse search(String keyword) {

        List<DtoRestaurantSummary> restaurants = searchByRestaurants(keyword);
        List<DtoMenuItemResponse> menuItems = searchByMenuItems(keyword);
        DtoSearchResponse response = new DtoSearchResponse();

        response.setRestaurants(restaurants);
        response.setMenuItems(menuItems);

        return response;
    }

    private List<DtoRestaurantSummary> searchByRestaurants(String keyword) {
        List<Restaurant> dbRestaurants = restRepo.searchRestaurants(keyword).stream()
                .filter(restaurant -> restaurant.getDeletedAt() == null).toList();
        List<DtoRestaurantSummary> response = new ArrayList<>();
        for (Restaurant restaurant : dbRestaurants) {
            DtoRestaurantSummary summary = new DtoRestaurantSummary();
            BeanUtils.copyProperties(restaurant, summary);
            response.add(summary);
        }
        return response;
    }

    private List<DtoMenuItemResponse> searchByMenuItems(String keyword) {
        List<MenuItem> menuItems = menuItemRepo.searchMenuItems(keyword).stream()
                .filter(menuItem -> menuItem.getDeletedAt() == null).toList();

        List<DtoMenuItemResponse> response = new ArrayList<>();
        for (MenuItem menuItem : menuItems) {
            DtoMenuItemResponse menuItemResponse = new DtoMenuItemResponse();
            BeanUtils.copyProperties(menuItem, menuItemResponse);
            response.add(menuItemResponse);
        }
        return response;
    }

}

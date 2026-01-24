package com.ismaildrcn.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildrcn.model.dto.DtoMenuCategoryRequest;
import com.ismaildrcn.model.dto.DtoMenuCategoryResponse;
import com.ismaildrcn.model.entity.MenuCategory;
import com.ismaildrcn.repository.MenuCategoryRepository;
import com.ismaildrcn.service.IMenuCategoryService;

@Service
public class MenuCategoryServiceImpl implements IMenuCategoryService {

    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    @Override
    public DtoMenuCategoryResponse saveMenuCategory(DtoMenuCategoryRequest request) {
        DtoMenuCategoryResponse response = new DtoMenuCategoryResponse();
        MenuCategory menuCategoryEntity = createMenuCategoryEntity(request);

        BeanUtils.copyProperties(menuCategoryEntity, response);
        return response;
    }

    @Override
    public List<DtoMenuCategoryResponse> getAllMenuCategories() {
        List<DtoMenuCategoryResponse> responseList = new ArrayList<>();
        List<MenuCategory> allMenuCategories = menuCategoryRepository.findAll();

        for (MenuCategory menuCategory : allMenuCategories) {
            DtoMenuCategoryResponse dto = new DtoMenuCategoryResponse();
            BeanUtils.copyProperties(menuCategory, dto);
            responseList.add(dto);
        }
        return responseList;
    }

    private MenuCategory createMenuCategoryEntity(DtoMenuCategoryRequest request) {
        MenuCategory menuCategory = new MenuCategory();
        BeanUtils.copyProperties(request, menuCategory);

        MenuCategory savedMenuCategory = menuCategoryRepository.save(menuCategory);
        return savedMenuCategory;
    }

}

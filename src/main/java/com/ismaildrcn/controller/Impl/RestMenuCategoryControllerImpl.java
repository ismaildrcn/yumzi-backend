package com.ismaildrcn.controller.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildrcn.controller.IRestMenuCategoryController;
import com.ismaildrcn.controller.RestBaseController;
import com.ismaildrcn.controller.RootEntity;
import com.ismaildrcn.model.dto.DtoMenuCategoryRequest;
import com.ismaildrcn.model.dto.DtoMenuCategoryResponse;
import com.ismaildrcn.service.IMenuCategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/menu-categories")
public class RestMenuCategoryControllerImpl extends RestBaseController implements IRestMenuCategoryController {

    @Autowired
    private IMenuCategoryService menuCategoryService;

    @Override
    @PostMapping("/save")
    public RootEntity<DtoMenuCategoryResponse> saveMenuCategory(@Valid @RequestBody DtoMenuCategoryRequest request) {
        return ok(menuCategoryService.saveMenuCategory(request));
    }

    @Override
    @GetMapping("/all")
    public RootEntity<List<DtoMenuCategoryResponse>> getAllMenuCategories() {
        return ok(menuCategoryService.getAllMenuCategories());
    }

}

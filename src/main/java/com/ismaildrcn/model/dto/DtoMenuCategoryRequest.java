package com.ismaildrcn.model.dto;

import com.ismaildrcn.model.enums.MenuCategoryType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoMenuCategoryRequest {

    @NotNull(message = "Name cannot be null")
    private String name;

    private String description;

    @NotNull(message = "Image URL cannot be null")
    private String imageUrl;

    private Boolean isActive;

    private MenuCategoryType categoryType = MenuCategoryType.STANDARD;
}

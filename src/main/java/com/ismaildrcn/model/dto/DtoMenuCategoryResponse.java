package com.ismaildrcn.model.dto;

import java.util.UUID;

import com.ismaildrcn.model.enums.MenuCategoryType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoMenuCategoryResponse {

    private UUID uniqueId;

    private String name;

    private String description;

    private String slug;

    private String imageUrl;

    private Integer sortOrder;

    private Boolean isActive;

    private Boolean isFeatured;

    private MenuCategoryType categoryType;

}

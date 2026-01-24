package com.ismaildrcn.model.enums;

import lombok.Getter;

@Getter
public enum MenuCategoryType {
    STANDARD("STANDARD"),
    PROMOTION("PROMOTION"),
    COMBO("COMBO"),
    CHEF_SPECIAL("CHEF_SPECIAL"),
    SEASONAL("SEASONAL"),
    KIDS("KIDS"),
    DRINKS("DRINKS"),
    DESSERT("DESSERTS"),
    APPETIZER("APPETIZERS"),
    BREAKFAST("BREAKFAST"),
    LUNCH("LUNCH"),
    DINNER("DINNER");

    private final String type;

    MenuCategoryType(String type) {
        this.type = type;
    }
}

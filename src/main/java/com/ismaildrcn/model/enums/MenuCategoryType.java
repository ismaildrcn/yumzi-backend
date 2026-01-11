package com.ismaildrcn.model.enums;

public enum MenuCategoryType {
    STANDARD("Standart"),
    PROMOTION("Promosyon"),
    COMBO("Combo Menu"),
    CHEF_SPECIAL("Chef's Special"),
    SEASONAL("Seasonal"),
    KIDS("Kids Menu"),
    DRINKS("Drinks"),
    DESSERT("Desserts"),
    APPETIZER("Appetizers"),
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner");

    private final String displayName;

    MenuCategoryType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

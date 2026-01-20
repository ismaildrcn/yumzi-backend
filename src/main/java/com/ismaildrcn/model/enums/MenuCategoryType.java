package com.ismaildrcn.model.enums;

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

    private final String displayName;

    MenuCategoryType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

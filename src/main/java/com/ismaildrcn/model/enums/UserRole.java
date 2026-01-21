package com.ismaildrcn.model.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");

    private String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

}

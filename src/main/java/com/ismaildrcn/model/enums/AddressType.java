package com.ismaildrcn.model.enums;

import lombok.Getter;

@Getter
public enum AddressType {
    HOME("Home"), WORK("Work"), OTHER("Other");

    private final String displayName;

    AddressType(String displayName) {
        this.displayName = displayName;
    }
}
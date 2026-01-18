package com.ismaildrcn.model.enums;

import lombok.Getter;

@Getter
public enum BankAccountType {
    PRIMARY("PRIMARY"),
    SECONDARY("SECONDARY"),
    BACKUP("BACKUP");

    private final String type;

    BankAccountType(String type) {
        this.type = type;
    }

}

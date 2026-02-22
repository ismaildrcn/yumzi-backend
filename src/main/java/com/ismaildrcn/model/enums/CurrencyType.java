package com.ismaildrcn.model.enums;

import lombok.Getter;

@Getter
public enum CurrencyType {
    TRY("TRY"),
    USD("USD"),
    EUR("EUR");

    private final String code;

    CurrencyType(String code) {
        this.code = code;
    }
}
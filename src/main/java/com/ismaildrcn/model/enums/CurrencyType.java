package com.ismaildrcn.model.enums;

import lombok.Getter;

@Getter
public enum CurrencyType {
    TRY("TRY"),
    USD("USD"),
    EUR("EUR"),
    GBP("GBP"),
    JPY("JPY"),
    AUD("AUD"),
    CAD("CAD"),
    CHF("CHF"),
    CNY("CNY"),
    SEK("SEK"),
    NZD("NZD");

    private final String code;

    CurrencyType(String code) {
        this.code = code;
    }
}
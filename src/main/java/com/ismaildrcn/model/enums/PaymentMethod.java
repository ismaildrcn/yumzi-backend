package com.ismaildrcn.model.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {

    CREDIT_CARD("CREDIT_CARD"),
    DEBIT_CARD("DEBIT_CARD"),
    CASH_ON_DELIVERY("CASH_ON_DELIVERY"),
    FOOD_CARD("FOOD_CARD");

    private final String method;

    PaymentMethod(String method) {
        this.method = method;
    }

}
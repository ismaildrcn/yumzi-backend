package com.ismaildrcn.model.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"),
    PREPARING("PREPARING"),
    READY_FOR_DELIVERY("READY_FOR_DELIVERY"),
    DRIVER_ASSIGNED("DRIVER_ASSIGNED"),
    DRIVER_EN_ROUTE_TO_RESTAURANT("DRIVER_EN_ROUTE_TO_RESTAURANT"),
    ON_THE_WAY("ON_THE_WAY"),
    DELIVERED("DELIVERED"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED"),
    REFUNDED("REFUNDED");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }
}

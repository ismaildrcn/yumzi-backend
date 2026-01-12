package com.ismaildrcn.model.enums;

import lombok.Getter;

@Getter
public enum OrderDeliveryType {
    DOOR("Kapıda Teslim"), // Müşterinin adresine
    PICKUP("Restorandan Al"), // Müşteri gelip alır

    CURBSIDE("Araçtan Teslim"), // Arabaya getirilir
    TABLE("Masada Teslim"), // Restoranda masaya
    LOCKER("Güvenli Kutu"), // Teslimat kutusuna
    DELIVERY_POINT("Teslimat Noktası"); // Belirlenen noktaya

    private final String displayName;

    OrderDeliveryType(String displayName) {
        this.displayName = displayName;
    }

}

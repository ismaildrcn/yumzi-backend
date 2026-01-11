package com.ismaildrcn.model.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    // Öncesi
    PENDING("PENDING"), // Beklemede
    AUTHORIZED("AUTHORIZED"), // Yetkilendirildi

    // Başarılı
    SUCCESSFUL("SUCCESSFUL"),
    COMPLETED("COMPLETED"),
    CAPTURED("CAPTURED"), // Tutar alındı

    // Beklemede
    PROCESSING("PROCESSING"),
    AWAITING_CONFIRMATION("AWAITING_CONFIRMATION"),

    // Başarısız
    FAILED("FAILED"),
    DECLINED("DECLINED"), // Reddedildi
    EXPIRED("EXPIRED"), // Süresi doldu

    // İade/İptal
    REFUNDED("REFUNDED"), // İade edildi
    PARTIALLY_REFUNDED("PARTIALLY_REFUNDED"), // Kısmen iade
    CANCELLED("CANCELLED"),

    // Riskli
    UNDER_REVIEW("UNDER_REVIEW"), // İnceleniyor
    FRAUD_SUSPECTED("FRAUD_SUSPECTED"),
    // Beklemede (kapıda ödeme için)
    AWAITING_COD_PAYMENT("AWAITING_COD_PAYMENT");

    private final String status;

    PaymentStatus(String status) {
        this.status = status;
    }
}
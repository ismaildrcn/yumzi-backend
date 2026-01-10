package com.ismaildrcn.model.enums;

public enum PaymentStatus {
    // Öncesi
    PENDING, // Beklemede
    AUTHORIZED, // Yetkilendirildi

    // Başarılı
    SUCCESSFUL,
    COMPLETED,
    CAPTURED, // Tutar alındı

    // Beklemede
    PROCESSING,
    AWAITING_CONFIRMATION,

    // Başarısız
    FAILED,
    DECLINED, // Reddedildi
    EXPIRED, // Süresi doldu

    // İade/İptal
    REFUNDED, // İade edildi
    PARTIALLY_REFUNDED, // Kısmen iade
    CANCELLED,

    // Riskli
    UNDER_REVIEW, // İnceleniyor
    FRAUD_SUSPECTED,

    // Beklemede (kapıda ödeme için)
    AWAITING_COD_PAYMENT
}

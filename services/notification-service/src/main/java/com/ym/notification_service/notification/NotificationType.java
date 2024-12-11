package com.ym.notification_service.notification;

import lombok.Getter;

@Getter
public enum NotificationType {

    ORDER_CONFIRMATION("order-confirmation.html", "Order Confirmation"),
    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment Confirmation");

    private final String template;
    private final String subject;

    NotificationType(String template, String subject){
        this.template = template;
        this.subject = subject;
    }

}

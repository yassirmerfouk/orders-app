package com.ym.payment_service.payment;

import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment mapToPayment(PaymentRequest paymentRequest){
        return Payment.builder()
                .amount(paymentRequest.getAmount())
                .paymentMethod(paymentRequest.getPaymentMethod())
                .orderId(paymentRequest.getOrderId())
                .build();
    }

    public PaymentConfirmation mapToPaymentConfirmation(
            Payment payment,
            PaymentRequest paymentRequest
    ){
        return PaymentConfirmation.builder()
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .date(payment.getCreatedAt())
                .orderReference(paymentRequest.getOrderReference())
                .firstName(paymentRequest.getFirstName())
                .lastName(paymentRequest.getLastName())
                .email(paymentRequest.getEmail())
                .build();
    }
}

package com.ym.order_service.payment;

import com.ym.order_service.customer.CustomerResponse;
import com.ym.order_service.order.Order;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentRequest mapToPaymentRequest(Order order, CustomerResponse customerResponse){
        return PaymentRequest.builder()
                .orderId(order.getId())
                .amount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .orderReference(order.getReference())
                .firstName(customerResponse.getFirstName())
                .lastName(customerResponse.getLastName())
                .email(customerResponse.getEmail())
                .build();
    }
}

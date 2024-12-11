package com.ym.order_service.order;

import com.ym.order_service.customer.CustomerResponse;
import com.ym.order_service.product.PurchaseResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderMapper {

    public Order mapToOrder(OrderRequest orderRequest, List<PurchaseResponse> purchaseResponses){
        return Order
                .builder()
                .reference(orderRequest.getReference())
                .paymentMethod(orderRequest.getPaymentMethod())
                .customerId(orderRequest.getCustomerId())
                .totalAmount(
                        BigDecimal.valueOf(
                                purchaseResponses.stream().mapToDouble(
                                        response -> response.getPrice().doubleValue() * response.getQuantity()
                                ).sum()
                        )
                ).orderLines(
                        purchaseResponses.stream().map(
                                response -> OrderLine.builder()
                                        .productId(response.getProductId())
                                        .price(response.getPrice())
                                        .quantity(response.getQuantity())
                                        .build()
                        ).toList()
                ).build();
    }

    public OrderConfirmation mapToOrderConfirmation(Order order, CustomerResponse customerResponse, List<PurchaseResponse> purchaseResponses){
        return OrderConfirmation.builder()
                .reference(order.getReference())
                .totalAmount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .date(order.getCreatedAt())
                .customer(customerResponse)
                .lines(purchaseResponses)
                .build();
    }

    public OrderResponse mapToOrderResponse(Order order, CustomerResponse customerResponse){
        return OrderResponse.builder()
                .id(order.getId())
                .reference(order.getReference())
                .totalAmount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .date(order.getCreatedAt())
                .customer(customerResponse)
                .build();
    }
}

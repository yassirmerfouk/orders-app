package com.ym.order_service.order;

import com.ym.order_service.customer.CustomerResponse;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class OrderResponse {

    private Long id;
    private String reference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private LocalDateTime date;
    private CustomerResponse customer;
}

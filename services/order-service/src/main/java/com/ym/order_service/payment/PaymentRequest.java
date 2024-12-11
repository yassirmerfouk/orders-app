package com.ym.order_service.payment;

import com.ym.order_service.order.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class PaymentRequest {

    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private Long orderId;
    private String orderReference;
    private String firstName;
    private String lastName;
    private String email;
}

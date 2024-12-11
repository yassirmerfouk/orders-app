package com.ym.payment_service.payment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class PaymentRequest {

    @NotNull(message = "Amount is required.")
    @Positive(message = "Amount is not valid.")
    private BigDecimal amount;
    @NotNull(message = "Payment method is required.")
    private PaymentMethod paymentMethod;
    @NotNull(message = "Order id is required.")
    private Long orderId;
    @NotNull(message = "Order reference is required.")
    private String orderReference;
    @NotNull(message = "Customer first name is required.")
    private String firstName;
    @NotNull(message = "Customer last name is required.")
    private String lastName;
    @NotNull(message = "Customer email is required.")
    private String email;
}

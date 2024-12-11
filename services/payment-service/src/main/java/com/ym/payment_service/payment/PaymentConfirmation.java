package com.ym.payment_service.payment;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class PaymentConfirmation {

    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String orderReference;
    private LocalDateTime date;
    private String firstName;
    private String lastName;
    private String email;
}

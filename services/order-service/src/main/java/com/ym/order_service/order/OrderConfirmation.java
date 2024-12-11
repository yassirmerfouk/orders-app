package com.ym.order_service.order;

import com.ym.order_service.customer.CustomerResponse;
import com.ym.order_service.product.PurchaseResponse;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class OrderConfirmation {

    private String reference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private LocalDateTime date;

    private CustomerResponse customer;

    private List<PurchaseResponse> lines;

}

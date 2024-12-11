package com.ym.order_service.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class OrderRequest {

    @NotBlank(message = "Reference is required.")
    private String reference;
    @NotNull(message = "Payment method is required.")
    private PaymentMethod paymentMethod;
    @NotNull(message = "Customer id is required.")
    private Long customerId;

    @NotNull(message = "Order lines are required.")
    @NotEmpty(message = "Order line should not be empty.")
    private List<@Valid OrderLineRequest> orderLines;

    @NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
    public static class OrderLineRequest{
        @NotNull(message = "Product id is required.")
        private Long productId;
        @NotNull(message = "Quantity is required.")
        @Positive(message = "Quantity is not valid.")
        private Integer quantity;
    }
}

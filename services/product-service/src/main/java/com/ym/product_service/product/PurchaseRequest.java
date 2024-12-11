package com.ym.product_service.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class PurchaseRequest {

    @NotNull(message = "Product id is required.")
    private Long productId;
    @NotNull(message = "Quantity is required.")
    @Positive(message = "Quantity is not valid, should be > 0.")
    private Integer quantity;
}

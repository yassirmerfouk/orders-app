package com.ym.product_service.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class ProductRequest {

    @NotBlank(message = "Name is required.")
    private String name;
    private String description;
    @NotNull(message = "Quantity is required.")
    @Positive(message = "Quantity is not valid.")
    private Integer availableQuantity;
    @NotNull(message = "Price is required.")
    @Positive(message = "Price is no valid.")
    private BigDecimal price;
    @NotNull(message = "Category is required.")
    private Long categoryId;
}

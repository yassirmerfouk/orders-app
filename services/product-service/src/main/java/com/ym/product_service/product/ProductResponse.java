package com.ym.product_service.product;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private int availableQuantity;
    private BigDecimal price;

    private Long categoryId;
    private String categoryName;
    private String categoryDescription;
}

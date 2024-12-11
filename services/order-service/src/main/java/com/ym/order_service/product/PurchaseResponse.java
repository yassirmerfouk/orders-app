package com.ym.order_service.product;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class PurchaseResponse {

    private Long productId;
    private String productName;
    private BigDecimal price;
    private int quantity;
    private String categoryName;

}

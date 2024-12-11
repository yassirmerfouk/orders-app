package com.ym.order_service.product;


import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class PurchaseRequest {

    private Long productId;
    private Integer quantity;
}

package com.ym.order_service.order;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_lines")
@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private int quantity;

    private Long productId;
}

package com.ym.notification_service.order;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class OrderConfirmation {

    private String reference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private LocalDateTime date;

    private Customer customer;

    private List<Product> lines;

    @NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
    public static class Customer{
        private Long id;
        private String firstName;
        private String lastName;
        private String email;

        public String getFullName(){
            return firstName + " " + lastName;
        }
    }

    @NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
    public static class Product{
        private Long productId;
        private String productName;
        private BigDecimal price;
        private int quantity;
        private String categoryName;
    }

}

package com.ym.customer_service.customer;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class CustomerResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AddressResponse address;
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @Builder
    public static class AddressResponse{
        private Long id;
        private String street;
        private String houseNumber;
        private String zipCode;
    }
}

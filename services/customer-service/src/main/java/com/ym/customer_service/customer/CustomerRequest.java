package com.ym.customer_service.customer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class CustomerRequest {

    @NotBlank(message = "Firstname is required.")
    private String firstName;
    @NotBlank(message = "Last name is required.")
    private String lastName;
    @NotBlank(message = "Email is required.")
    @Email(message = "Email is not valid.")
    private String email;
    @Valid
    @NotNull(message = "Address is required.")
    private AddressRequest address;
    @NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
    public static class AddressRequest{
        private Long id;
        @NotBlank(message = "Street is required.")
        private String street;
        @NotBlank(message = "House number is required.")
        private String houseNumber;
        @NotBlank(message = "Zipcode is required")
        private String zipCode;
    }
}

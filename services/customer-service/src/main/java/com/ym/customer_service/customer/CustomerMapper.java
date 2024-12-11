package com.ym.customer_service.customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer mapToCustomer(CustomerRequest customerRequest){
        if(customerRequest == null)
            return Customer.builder().build();
        return Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .email(customerRequest.getEmail())
                .address(
                        Address.builder()
                                .id(customerRequest.getAddress().getId())
                                .street(customerRequest.getAddress().getStreet())
                                .houseNumber(customerRequest.getAddress().getHouseNumber())
                                .zipCode(customerRequest.getAddress().getZipCode())
                                .build()
                ).build();
    }

    public CustomerResponse mapToCustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .address(
                        CustomerResponse.AddressResponse.builder()
                                .id(customer.getAddress().getId())
                                .street(customer.getAddress().getStreet())
                                .houseNumber(customer.getAddress().getHouseNumber())
                                .zipCode(customer.getAddress().getZipCode())
                                .build()
                ).build();
    }
}

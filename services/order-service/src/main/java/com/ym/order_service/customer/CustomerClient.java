package com.ym.order_service.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerClient {

    @GetMapping("/api/customers/{id}")
    CustomerResponse getCustomerById(@PathVariable Long id);

    @GetMapping("/api/customers")
    List<CustomerResponse> getCustomers();

    @GetMapping("/api/customers/by-ids")
    List<CustomerResponse> getCustomersByIds(@RequestParam List<Long> ids);
}

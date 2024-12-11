package com.ym.customer_service.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> addCustomer(
            @Valid @RequestBody CustomerRequest customerRequest
    ){
        return new ResponseEntity<>(
                customerService.addCustomer(customerRequest),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequest customerRequest
    ){
        return new ResponseEntity<>(
                customerService.updateCustomer(id, customerRequest),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(
            @PathVariable Long id
    ){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomers(){
        return new ResponseEntity<>(
                customerService.getCustomers(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(
                customerService.getCustomerById(id),
                HttpStatus.OK
        );
    }

    @GetMapping("/by-ids")
    public ResponseEntity<List<CustomerResponse>> getCustomersByIds(
            @RequestParam List<Long> ids
    ){
        return new ResponseEntity<>(
                customerService.getCustomersByIds(ids),
                HttpStatus.OK
        );
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> checkExistsById(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(
                customerService.checkExistsById(id),
                HttpStatus.OK
        );
    }

}

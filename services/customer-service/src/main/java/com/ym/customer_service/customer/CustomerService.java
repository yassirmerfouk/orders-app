package com.ym.customer_service.customer;

import com.ym.customer_service.exception.CustomException;
import com.ym.customer_service.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.*;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public Long addCustomer(CustomerRequest customerRequest){
        Customer customer = customerMapper.mapToCustomer(customerRequest);
        customerRepository.save(customer);
        return customer.getId();
    }

    public Long updateCustomer(Long id, CustomerRequest customerRequest){
        if(!customerRepository.existsById(id))
            throw new EntityNotFoundException(format("Customer %d not found.", id));
        Customer customer = customerMapper.mapToCustomer(customerRequest);
        customer.setId(id);
        customerRepository.save(customer);
        return customer.getId();
    }

    public void deleteCustomer(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format("Customer %d not found.", id))
        );
        customerRepository.delete(customer);
    }

    public List<CustomerResponse> getCustomers(){
        return customerRepository.findAll().stream()
                .map(customerMapper::mapToCustomerResponse)
                .toList();
    }

    public CustomerResponse getCustomerById(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format("Customer %d not found.", id))
        );
        return customerMapper.mapToCustomerResponse(customer);
    }

    public List<CustomerResponse> getCustomersByIds(List<Long> ids){
        return customerRepository.findAllById(ids).stream()
                .map(customerMapper::mapToCustomerResponse)
                .toList();
    }

    public boolean checkExistsById(Long id){
        return customerRepository.findById(id).isPresent();
    }
}

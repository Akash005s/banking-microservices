package com.easybank.app.mapper;


import com.easybank.app.dto.request.CustomerRequest;
import com.easybank.app.dto.response.CustomerResponse;
import com.easybank.app.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final AccountMapper accountMapper;

    public Customer toEntity(CustomerRequest request) {

        return Customer.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .accounts(new ArrayList<>())
                .active(true)
                .build();
    }

    public CustomerResponse toResponse(Customer customer) {
        return CustomerResponse.builder()
                .customerId(customer.getId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .mobile(customer.getMobileNumber())
                .active(customer.getActive())
                .accounts(
                        customer.getAccounts()
                                .stream()
                                .map(accountMapper::toResponse)
                                .toList()
                )
                .build();
    }
}

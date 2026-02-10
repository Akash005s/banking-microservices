package com.easybank.app.service.impl;

import com.easybank.app.constant.AccountExceptionConstant;
import com.easybank.app.dto.request.CustomerRequest;
import com.easybank.app.dto.request.UpdateAccountRequest;
import com.easybank.app.dto.response.*;
import com.easybank.app.entity.Account;
import com.easybank.app.entity.Customer;
import com.easybank.app.exception.AccountException;
import com.easybank.app.mapper.AccountMapper;
import com.easybank.app.mapper.CustomerMapper;
import com.easybank.app.repository.AccountRepository;
import com.easybank.app.repository.CustomerRepository;
import com.easybank.app.service.ICustomerService;
import com.easybank.app.service.client.CardFeignClient;
import com.easybank.app.service.client.LoanFeignClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final CustomerMapper customerMapper;
    private final LoanFeignClient loanFeignClient;
    private final CardFeignClient cardFeignClient;


    @Override
    @Transactional
    public void createAccount(CustomerRequest request) {

        Customer customer = customerRepository
                .findByMobileNumber(request.getMobileNumber())
                .orElseGet(() -> {
                    Customer newCustomer = customerMapper.toEntity(request);
                    return customerRepository.save(newCustomer);
                });

        customer.setFullName(request.getFullName());
        customer.setEmail(request.getEmail());

        Account account = accountMapper.toEntity(request.getAccountRequest());
        account.setCustomer(customer);

        customer.getAccounts().add(account);

        customerRepository.save(customer);
    }


    @Override
    public CustomerResponse fetchCustomer(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new AccountException(AccountExceptionConstant.CUSTOMER_NOT_FOUND));

        return customerMapper.toResponse(customer);
    }

    @Override
    @Transactional
    @Modifying
    public void updateAccount(String accountNumber, UpdateAccountRequest accountRequest) {
        Account account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new AccountException(AccountExceptionConstant.ACCOUNT_NOT_FOUND));

        account.setAccountType(accountRequest.getAccountType());
        account.setBalance(accountRequest.getBalance());
        accountRepository.save(account);
    }

    @Override
    @Transactional
    @Modifying
    public void deleteAccount(String accountNumber) {
        accountRepository.findById(accountNumber)
                .orElseThrow(() -> new AccountException(AccountExceptionConstant.ACCOUNT_NOT_FOUND));
        accountRepository.deleteById(accountNumber);
    }

    @Override
    public CustomerDetailsResponse fetchCustomerDetails(String mobileNumber) {

        CustomerResponse customerResponse = fetchCustomer(mobileNumber);

        LoanResponse loan = Optional
                .ofNullable(loanFeignClient.fetchLoan(mobileNumber))
                .map(ResponseEntity::getBody)
                .orElse(null);

        List<CardResponse> cards = Optional
                .ofNullable(cardFeignClient.fetchCard(mobileNumber))
                .map(ResponseEntity::getBody)
                .map(GenericResponse::getData)
                .orElseGet(ArrayList::new);

        // 4️⃣ Build response
        return CustomerDetailsResponse.builder()
                .customer(customerResponse)
                .loan(loan)
                .cards(cards)
                .build();
    }

}

package com.easybank.app.service;

import com.easybank.app.dto.request.CustomerRequest;
import com.easybank.app.dto.request.UpdateAccountRequest;
import com.easybank.app.dto.response.CustomerDetailsResponse;
import com.easybank.app.dto.response.CustomerResponse;

public interface ICustomerService {

    void createAccount(CustomerRequest customerRequest);
    CustomerResponse fetchCustomer(String mobileNumber);
    void updateAccount(String accountNumber, UpdateAccountRequest accountRequest);
    void deleteAccount(String accountNumber);
    CustomerDetailsResponse fetchCustomerDetails(String mobileNumber);
}

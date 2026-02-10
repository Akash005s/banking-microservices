package com.easybank.app.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerDetailsResponse {

    private CustomerResponse customer;
    private LoanResponse loan;
    private List<CardResponse> cards;
}

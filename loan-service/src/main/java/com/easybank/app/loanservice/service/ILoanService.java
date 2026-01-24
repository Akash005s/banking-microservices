package com.easybank.app.loanservice.service;

import com.easybank.app.loanservice.dto.request.LoanRequest;
import com.easybank.app.loanservice.dto.request.LoanUpdateRequest;
import com.easybank.app.loanservice.dto.response.LoanResponse;

public interface ILoanService {

    void createLoan(LoanRequest loanRequest);
    LoanResponse fetchLoan(String mobileNumber);
    void updateLoan(Long loanId, LoanUpdateRequest loanUpdateRequest);
    void deleteLoan(Long loanId);
}

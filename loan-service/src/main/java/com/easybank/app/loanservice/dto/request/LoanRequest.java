package com.easybank.app.loanservice.dto.request;

import com.easybank.app.loanservice.constant.LoanType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanRequest {

    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;

    @NotNull(message = "Loan type is required")
    private LoanType loanType;

    @NotNull(message = "Total loan amount is required")
    private BigDecimal totalLoan;
}


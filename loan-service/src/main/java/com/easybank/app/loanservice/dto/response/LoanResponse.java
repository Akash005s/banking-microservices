package com.easybank.app.loanservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LoanResponse {

    private Long loanId;
    private String loanType;
    private BigDecimal totalLoan;
    private BigDecimal amountPaid;
    private BigDecimal outstandingAmount;
}


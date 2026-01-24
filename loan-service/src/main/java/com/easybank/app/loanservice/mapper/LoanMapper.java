package com.easybank.app.loanservice.mapper;

import com.easybank.app.loanservice.dto.request.LoanRequest;
import com.easybank.app.loanservice.dto.response.LoanResponse;
import com.easybank.app.loanservice.entity.Loan;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class LoanMapper {

    public Loan mapToEntity(LoanRequest request) {
        return Loan.builder()
                .mobileNumber(request.getMobileNumber())
                .loanType(request.getLoanType().name())
                .totalLoan(request.getTotalLoan())
                .amountPaid(BigDecimal.ZERO)
                .outstandingAmount(request.getTotalLoan())
                .build();
    }

    public LoanResponse mapToResponse(Loan loan) {
        return LoanResponse.builder()
                .loanId(loan.getLoanId())
                .loanType(loan.getLoanType())
                .totalLoan(loan.getTotalLoan())
                .amountPaid(loan.getAmountPaid())
                .outstandingAmount(loan.getOutstandingAmount())
                .build();
    }
}


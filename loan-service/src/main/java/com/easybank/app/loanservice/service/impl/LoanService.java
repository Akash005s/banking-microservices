package com.easybank.app.loanservice.service.impl;

import com.easybank.app.loanservice.constant.LoanExceptionConstant;
import com.easybank.app.loanservice.dto.request.LoanRequest;
import com.easybank.app.loanservice.dto.request.LoanUpdateRequest;
import com.easybank.app.loanservice.dto.response.LoanResponse;
import com.easybank.app.loanservice.entity.Loan;
import com.easybank.app.loanservice.exception.LoanExcepiton;
import com.easybank.app.loanservice.mapper.LoanMapper;
import com.easybank.app.loanservice.repository.LoanRepository;
import com.easybank.app.loanservice.service.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanService implements ILoanService {

    private final LoanRepository loanRepository;

    @Override
    public void createLoan(LoanRequest loanRequest) {

        if (loanRepository.existsByMobileNumber(loanRequest.getMobileNumber())) {
            throw new LoanExcepiton(LoanExceptionConstant.LOAN_ALREADY_EXISTS, "Loan already exists for this mobile number");
        }

        Loan loan = LoanMapper.mapToEntity(loanRequest);
        loanRepository.save(loan);
    }

    @Override
    public LoanResponse fetchLoan(String mobileNumber) {

        Loan loan = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new LoanExcepiton(LoanExceptionConstant.LOAN_NOT_FOUND,
                        "Loan not found for this mobile number"
                ));
        return LoanMapper.mapToResponse(loan);
    }

    @Override
    public void updateLoan(Long loanId, LoanUpdateRequest loanUpdateRequest) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanExcepiton(LoanExceptionConstant.LOAN_NOT_FOUND,
                        "Loan not found for this loan id"
                ));

        loan.setAmountPaid(
                loan.getAmountPaid().add(loanUpdateRequest.getAmountPaid())
        );
        loan.setOutstandingAmount(
                loan.getTotalLoan().subtract(loan.getAmountPaid())
        );
        loanRepository.save(loan);
    }

    @Override
    public void deleteLoan(Long loanId) {
        if(!loanRepository.existsById(loanId)){
            throw new LoanExcepiton(LoanExceptionConstant.LOAN_NOT_FOUND, "Loan not found for this loan id.");
        }

        loanRepository.deleteById(loanId);
    }
}

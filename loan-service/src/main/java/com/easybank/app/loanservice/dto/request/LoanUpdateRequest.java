package com.easybank.app.loanservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanUpdateRequest {

    @NotNull(message = "Loan amount paid is required")
    private BigDecimal amountPaid;
}


package com.easybank.app.dto.request;

import com.easybank.app.constant.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateAccountRequest {

    @NotNull(message = "Account type is required")
    private AccountType accountType;

    @NotNull(message = "Balance is required")
    @DecimalMin(value = "0.0", message = "Balance must be zero or greater")
    private BigDecimal balance;
}

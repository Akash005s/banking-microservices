package com.easybank.app.dto.request;

import com.easybank.app.constant.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountRequest {
    /**
     * Type of account to be created.
     * Example: SAVINGS, CURRENT, SALARY
     */
    @NotNull(message = "Account type is required")
    private AccountType accountType;

    /**
     * Initial deposit amount while opening account.
     */
    @NotNull(message = "Initial balance is required")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "Initial balance must be zero or greater")
    private BigDecimal initialBalance;
}

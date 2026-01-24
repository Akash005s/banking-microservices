package com.easybank.app.cardservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateCardRequest {

    @NotNull(message = "Amount used can not be null.")
    private BigDecimal amountUsed;
}

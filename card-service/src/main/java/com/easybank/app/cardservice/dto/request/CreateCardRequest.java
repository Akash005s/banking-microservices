package com.easybank.app.cardservice.dto.request;

import com.easybank.app.cardservice.constant.CardType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateCardRequest {

    @NotBlank(message = "Mobile number should be a valid-10 digit number")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
    private String mobileNumber;

    @NotNull(message = "Card type should be CREDIT_CARD/DEBIT_CARD.")
    private CardType cardType;

    @NotNull
    private BigDecimal totalLimit;
}

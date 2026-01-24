package com.easybank.app.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO used to create a new customer and open a bank account.
 */
@Data
public class CustomerRequest {

    /**
     * Customer full name.
     */
    @NotBlank(message = "Customer name is required")
    @Size(min = 3, max = 50, message = "Customer name must be between 3 and 50 characters")
    private String fullName;

    /**
     * Customer email address.
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    /**
     * Customer mobile number.
     */
    @NotBlank(message = "Mobile number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Mobile number must be a valid 10-digit Indian number"
    )
    private String mobileNumber;

    /**
     * Account request details.
     */
    @Valid
    private AccountRequest accountRequest;
}

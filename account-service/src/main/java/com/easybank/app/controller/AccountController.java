package com.easybank.app.controller;

import com.easybank.app.config.ContactInfoProperties;
import com.easybank.app.dto.request.CustomerRequest;
import com.easybank.app.dto.request.UpdateAccountRequest;
import com.easybank.app.dto.response.CustomerDetailsResponse;
import com.easybank.app.dto.response.CustomerResponse;
import com.easybank.app.dto.response.GenericResponse;
import com.easybank.app.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "Account Controller",
        description = "REST APIs in EasyBank to CREATE, FETCH, UPDATE AND DELETE account details"
)
public class AccountController {

    private final ICustomerService customerService;
    private final ContactInfoProperties contactInfo;

    @Value("${build.version}")
    private String buildVersion;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer & Account inside EasyBank"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = GenericResponse.class)))
    })
    @PostMapping("/create")
    ResponseEntity<GenericResponse<Void>> createAccount(@Valid @RequestBody CustomerRequest customerRequest){
        customerService.createAccount(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponse<>("Account Succesfully Created.", null));
    }

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer & Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error")
    })
    @GetMapping("/fetch")
    ResponseEntity<GenericResponse<CustomerResponse>> fetchAccount(
            @NotBlank(message = "Mobile number is required")
            @Pattern(
                    regexp = "^[6-9]\\d{9}$",
                    message = "Mobile number must be a valid 10-digit Indian number"
            )
            @RequestParam String mobileNumber){
        CustomerResponse response = customerService.fetchCustomer(mobileNumber);
        return ResponseEntity.ok(new GenericResponse<>("Account fetched successfully.", response));
    }

    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer & Account details based on an account number"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "HTTP Status NO CONTENT"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error")
    })
    @PutMapping("/update")
    ResponseEntity<GenericResponse<Void>> updateAccount(
            @NotBlank(message = "Account number is required")
            @Pattern(
                    regexp = "^[5-9]\\d{11}$",
                    message = "Account number must be a 12-digit number starting with 5–9"
            )
            @RequestParam String accountNumber,
            @Valid @RequestBody UpdateAccountRequest accountRequest){
        customerService.updateAccount(accountNumber, accountRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new GenericResponse<>("Account Updated Successfully.", null));
    }

    @Operation(
            summary = "Delete Account REST API",
            description = "REST API to delete Customer & Account details based on an account number"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "HTTP Status NO CONTENT"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error")
    })
    @DeleteMapping("/delete")
    ResponseEntity<GenericResponse<GenericResponse<Void>>> deleteAccount(
            @NotBlank(message = "Mobile number is required")
            @Pattern(
                    regexp = "^[5-9]\\d{11}$",
                    message = "Account number must be a 12-digit number starting with 5–9"
            )
            @RequestParam String accountNumber){
        customerService.deleteAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new GenericResponse<>("Account deleted successfully.", null));
    }

    @GetMapping("/contact-info")
    ResponseEntity<GenericResponse<ContactInfoProperties>> fetchContactInfo(){
        return ResponseEntity.ok(new GenericResponse<>("Fetched Contact Info", contactInfo));
    }

    @GetMapping("/build-version")
    ResponseEntity<GenericResponse<String>> fetchBuildVersion(){
        return ResponseEntity.ok(new GenericResponse<>("Fetched Build Version", buildVersion));
    }

    @GetMapping("/fetch/customer-details")
    public ResponseEntity<CustomerDetailsResponse> fetchCustomerDetails(
            @RequestHeader("EasyBank-Correlation-Id") String correlationId,
            @RequestParam("mobileNumber") String mobileNumber) {
        log.debug("Easybank-Correlation-Id found: {}", correlationId);
        CustomerDetailsResponse response =
                customerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.ok(response);
    }
}

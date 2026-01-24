package com.easybank.app.loanservice.controller;

import com.easybank.app.loanservice.dto.request.LoanRequest;
import com.easybank.app.loanservice.dto.request.LoanUpdateRequest;
import com.easybank.app.loanservice.dto.response.LoanResponse;
import com.easybank.app.loanservice.service.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
@Tag(name = "Loan Service", description = "APIs related to Loan Management")
public class LoanController {

    private final ILoanService loanService;

    @Operation(
            summary = "Create a new loan",
            description = "Creates a new loan for a customer using mobile number",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Loan created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request"),
                    @ApiResponse(responseCode = "409", description = "Loan already exists")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<String> createLoan(
            @Valid @RequestBody LoanRequest loanRequest) {

        loanService.createLoan(loanRequest);
        return new ResponseEntity<>("Loan created successfully", HttpStatus.CREATED);
    }

    @Operation(
            summary = "Fetch loan details",
            description = "Fetch loan details using mobile number",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Loan fetched successfully",
                            content = @Content(schema = @Schema(implementation = LoanResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Loan not found")
            }
    )
    @GetMapping("/fetch/{mobileNumber}")
    public ResponseEntity<LoanResponse> fetchLoan(
            @Parameter(description = "Customer mobile number", example = "9876543210")
            @PathVariable String mobileNumber) {

        return ResponseEntity.ok(loanService.fetchLoan(mobileNumber));
    }

    @Operation(
            summary = "Update loan payment",
            description = "Updates paid amount for a given loan ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Loan updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Loan not found")
            }
    )
    @PutMapping("/update/{loanId}")
    public ResponseEntity<String> updateLoan(
            @Parameter(description = "Loan ID", example = "101")
            @PathVariable Long loanId,
            @Valid @RequestBody LoanUpdateRequest loanUpdateRequest) {

        loanService.updateLoan(loanId, loanUpdateRequest);
        return ResponseEntity.ok("Loan updated successfully");
    }

    @Operation(
            summary = "Delete loan",
            description = "Deletes loan using loan ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Loan deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Loan not found")
            }
    )
    @DeleteMapping("/delete/{loanId}")
    public ResponseEntity<String> deleteLoan(
            @Parameter(description = "Loan ID", example = "101")
            @PathVariable Long loanId) {

        loanService.deleteLoan(loanId);
        return ResponseEntity.ok("Loan deleted successfully");
    }
}


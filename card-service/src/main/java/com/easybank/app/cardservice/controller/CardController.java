package com.easybank.app.cardservice.controller;

import com.easybank.app.cardservice.config.ContactInfoProperties;
import com.easybank.app.cardservice.dto.request.CreateCardRequest;
import com.easybank.app.cardservice.dto.request.UpdateCardRequest;
import com.easybank.app.cardservice.dto.response.CardResponse;
import com.easybank.app.cardservice.dto.response.GenericResponse;
import com.easybank.app.cardservice.service.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
@Validated
public class CardController {

    private final ICardService cardService;
    private final ContactInfoProperties contactInfo;

    @Value("${build.version}")
    private String buildVersion;

    @Operation(
            summary = "Create a new card",
            description = "Creates a new debit/credit card for a given mobile number"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Card created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<GenericResponse<Void>> createCard(
            @Valid @RequestBody CreateCardRequest cardRequest
    ) {
        cardService.createCard(cardRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new GenericResponse<>("Card created successfully.", null));
    }

    @Operation(
            summary = "Fetch cards by mobile number",
            description = "Fetch all cards linked with a mobile number"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cards fetched successfully"),
            @ApiResponse(responseCode = "404", description = "No cards found", content = @Content)
    })
    @GetMapping("/fetch")
    public ResponseEntity<GenericResponse<List<CardResponse>>> fetchCard(
            @Parameter(
                    description = "Registered mobile number",
                    example = "9876543210",
                    required = true
            )
            @NotBlank(message = "Mobile number is required")
            @Pattern(
                    regexp = "^[6-9]\\d{9}$",
                    message = "Mobile number must be a valid 10-digit Indian number"
            )
            @RequestParam String mobileNumber
    ) {
        List<CardResponse> cards = cardService.fetchCard(mobileNumber);
        return ResponseEntity.ok(new GenericResponse<>("Cards fetched successfully.", cards));
    }

    @Operation(
            summary = "Update card details",
            description = "Update card usage details using cardId"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Card updated successfully"),
            @ApiResponse(responseCode = "404", description = "Card not found", content = @Content)
    })
    @PutMapping("/update/{cardId}")
    public ResponseEntity<GenericResponse<Void>> updateCard(
            @Parameter(description = "Card ID", example = "101")
            @PathVariable Long cardId,

            @Valid @RequestBody UpdateCardRequest cardRequest
    ) {
        cardService.updateCard(cardId, cardRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new GenericResponse<>("Card updated successfully.", null));
    }

    @Operation(
            summary = "Delete card",
            description = "Deletes a card using cardId"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Card deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Card not found", content = @Content)
    })
    @DeleteMapping("/delete/{cardId}")
    public ResponseEntity<GenericResponse<Void>> deleteCard(
            @Parameter(description = "Card ID", example = "101")
            @PathVariable Long cardId
    ) {
        cardService.deleteCard(cardId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new GenericResponse<>("Card deleted successfully.", null));
    }

    @GetMapping("/contact-info")
    ResponseEntity<GenericResponse<ContactInfoProperties>> fetchContactInfo(){
        return ResponseEntity.ok(new GenericResponse<>("Fetched Contact Info", contactInfo));
    }

    @GetMapping("/build-version")
    ResponseEntity<GenericResponse<String>> fetchBuildVersion(){
        return ResponseEntity.ok(new GenericResponse<>("Fetched Build Version", buildVersion));
    }
}

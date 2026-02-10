package com.easybank.app.service.client;

import com.easybank.app.dto.response.CardResponse;
import com.easybank.app.dto.response.GenericResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("cards")
public interface CardFeignClient {

    @GetMapping("/cards/fetch")
    ResponseEntity<GenericResponse<List<CardResponse>>> fetchCard(@RequestParam String mobileNumber);
}

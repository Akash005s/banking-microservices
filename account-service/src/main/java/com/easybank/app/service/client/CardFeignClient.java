package com.easybank.app.service.client;

import com.easybank.app.dto.response.CardResponse;
import com.easybank.app.dto.response.GenericResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name= "cards", fallback = CardsFallback.class)
public interface CardFeignClient {

    @GetMapping("/cards/fetch")
    ResponseEntity<GenericResponse<List<CardResponse>>> fetchCard(@RequestParam String mobileNumber);
}

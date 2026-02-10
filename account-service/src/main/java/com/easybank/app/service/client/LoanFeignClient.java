package com.easybank.app.service.client;

import com.easybank.app.dto.response.LoanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("loans")
public interface LoanFeignClient {

    @GetMapping("/loans/fetch/{mobileNumber}")
    ResponseEntity<LoanResponse> fetchLoan(@PathVariable String mobileNumber);
}

package com.easybank.app.service.client;

import com.easybank.app.dto.response.LoanResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoanFeignClient{

    @Override
    public ResponseEntity<LoanResponse> fetchLoan(String mobileNumber) {
        return null;
    }
}

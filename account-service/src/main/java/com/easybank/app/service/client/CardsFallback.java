package com.easybank.app.service.client;

import com.easybank.app.dto.response.CardResponse;
import com.easybank.app.dto.response.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardsFallback implements CardFeignClient{

    @Override
    public ResponseEntity<GenericResponse<List<CardResponse>>> fetchCard(String mobileNumber) {
        return null;
    }
}

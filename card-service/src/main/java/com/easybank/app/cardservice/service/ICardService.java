package com.easybank.app.cardservice.service;

import com.easybank.app.cardservice.dto.request.CreateCardRequest;
import com.easybank.app.cardservice.dto.request.UpdateCardRequest;
import com.easybank.app.cardservice.dto.response.CardResponse;

import java.util.List;

public interface ICardService {

    void createCard(CreateCardRequest request);

    List<CardResponse> fetchCard(String mobileNumber);

    void updateCard(Long cardId, UpdateCardRequest request);

    void deleteCard(Long cardId);
}

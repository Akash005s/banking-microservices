package com.easybank.app.cardservice.service.impl;

import com.easybank.app.cardservice.constant.CardExceptionConstant;
import com.easybank.app.cardservice.dto.request.CreateCardRequest;
import com.easybank.app.cardservice.dto.request.UpdateCardRequest;
import com.easybank.app.cardservice.dto.response.CardResponse;
import com.easybank.app.cardservice.entity.Card;
import com.easybank.app.cardservice.exception.CardExcepiton;
import com.easybank.app.cardservice.mapper.CardMapper;
import com.easybank.app.cardservice.repository.CardRepository;
import com.easybank.app.cardservice.service.ICardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService implements ICardService {

    private final CardRepository cardRepository;

    @Override
    public void createCard(CreateCardRequest request) {

        Card card = CardMapper.toEntity(request);
        cardRepository.save(card);
    }

    @Override
    public List<CardResponse> fetchCard(String mobileNumber) {

        List<Card> cards = cardRepository.findByMobileNumber(mobileNumber);

        if (cards.isEmpty()) {
            throw new CardExcepiton(CardExceptionConstant.CARD_NOT_FOUND, "Cards not found for this mobile number");
        }

        return cards.stream()
                .map(CardMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void updateCard(Long cardId, UpdateCardRequest request) {

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardExcepiton(CardExceptionConstant.CARD_NOT_FOUND));

        card.setAmountUsed(request.getAmountUsed());
        card.setAvailableAmount(card.getTotalLimit().subtract(request.getAmountUsed()));

        cardRepository.save(card);
    }

    @Override
    public void deleteCard(Long cardId) {

        if(!cardRepository.existsById(cardId)){
            throw new CardExcepiton(CardExceptionConstant.CARD_NOT_FOUND);
        }

        cardRepository.deleteById(cardId);
    }
}

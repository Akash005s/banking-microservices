package com.easybank.app.cardservice.mapper;

import com.easybank.app.cardservice.dto.request.CreateCardRequest;
import com.easybank.app.cardservice.dto.response.CardResponse;
import com.easybank.app.cardservice.entity.Card;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.security.SecureRandom;

@UtilityClass
public class CardMapper {

    private static final SecureRandom RANDOM = new SecureRandom();

    public Card toEntity(CreateCardRequest request) {
        return Card.builder()
                .mobileNumber(request.getMobileNumber())
                .cardNumber(generateCardNumber())
                .cardType(request.getCardType())
                .totalLimit(request.getTotalLimit())
                .amountUsed(BigDecimal.ZERO)
                .availableAmount(request.getTotalLimit())
                .build();
    }

    public CardResponse toResponse(Card card) {
        return CardResponse.builder()
                .cardId(card.getCardId())
                .cardNumber(card.getCardNumber())
                .cardType(card.getCardType().name())
                .totalLimit(card.getTotalLimit())
                .amountUsed(card.getAmountUsed())
                .availableAmount(card.getAvailableAmount())
                .build();
    }

    private String generateCardNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }
}

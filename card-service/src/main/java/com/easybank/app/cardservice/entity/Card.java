package com.easybank.app.cardservice.entity;

import com.easybank.app.cardservice.constant.CardType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false, unique = true)
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType cardType;

    @Column(nullable = false)
    private BigDecimal totalLimit;

    @Column(nullable = false)
    private BigDecimal amountUsed;

    @Column(nullable = false)
    private BigDecimal availableAmount;
}

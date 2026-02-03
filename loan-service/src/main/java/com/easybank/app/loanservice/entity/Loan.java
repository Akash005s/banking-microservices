package com.easybank.app.loanservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "loans", schema = "loan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String loanType;

    @Column(nullable = false)
    private BigDecimal totalLoan;

    @Column(nullable = false)
    private BigDecimal amountPaid;

    @Column(nullable = false)
    private BigDecimal outstandingAmount;
}


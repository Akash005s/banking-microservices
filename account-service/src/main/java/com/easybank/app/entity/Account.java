package com.easybank.app.entity;

import com.easybank.app.constant.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


/**
 * Entity representing a bank account.
 * <p>
 * Each account belongs to exactly one customer.
 * Stores account details such as balance and type.
 */
@Entity
@Table(name = "accounts", schema = "account")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity{


    /**
     * Unique account number.
     */
    @Id
    @Column(nullable = false, unique = true)
    private String accountNumber;

    /**
     * Type of account (SAVINGS, CURRENT, etc.)
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    /**
     * Current account balance.
     */
    @Column(nullable = false)
    private BigDecimal balance;

    /**
     * Flag to indicate if account is active.
     */
    @Column(nullable = false)
    private Boolean active;

    /**
     * Branch Name.
     */
    @Column(nullable = false)
    private String branch;

    /**
     * Customer who owns this account.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}


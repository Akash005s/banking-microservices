package com.easybank.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a bank customer.
 * <p>
 * A customer can hold multiple bank accounts.
 * This entity stores basic customer details and audit information.
 */
@Entity
@Table(name = "customers", schema = "account")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity{

    /**
     * Unique identifier for the customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Full name of the customer.
     */
    @Column(nullable = false)
    private String fullName;

    /**
     * Email address of the customer.
     * Must be unique.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Mobile number of the customer.
     */
    @Column(nullable = false, unique = true, length = 15)
    private String mobileNumber;

    /**
     * Indicates whether the customer is active.
     */
    @Column(nullable = false)
    private Boolean active;

    /**
     * List of accounts owned by the customer.
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> accounts;

}

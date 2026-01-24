package com.easybank.app.loanservice.repository;

import com.easybank.app.loanservice.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    boolean existsByMobileNumber(String mobileNumber);
    Optional<Loan> findByMobileNumber(String mobileNumber);
}

package com.easybank.app.repository;

import com.easybank.app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    boolean existsByAccountNumber(String accountNumber);
}

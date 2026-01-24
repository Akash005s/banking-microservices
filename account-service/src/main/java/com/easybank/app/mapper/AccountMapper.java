package com.easybank.app.mapper;

import com.easybank.app.dto.request.AccountRequest;
import com.easybank.app.dto.response.AccountResponse;
import com.easybank.app.entity.Account;
import com.easybank.app.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class AccountMapper {

    @Value("${bank.branch.name}")
    private String branchName;

    @Value("${bank.branch.code}")
    private String branchCode;

    private final AccountRepository accountRepository;

    public Account toEntity(AccountRequest accountRequest){
        return Account.builder()
                .accountNumber(generateUniqueAccountNumber().toString())
                .accountType(accountRequest.getAccountType())
                .balance(accountRequest.getInitialBalance())
                .branch(branchName)
                .active(true)
                .build();
    };

    public AccountResponse toResponse(Account account){
        return AccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType().name())
                .balance(account.getBalance())
                .branch(account.getBranch())
                .active(account.getActive())
                .build();
    }

    private Long generateUniqueAccountNumber() {
        Long accountNumber;
        Random random = new Random();

        do {
            int randomPart = 100000 + random.nextInt(900000);
            accountNumber = Long.parseLong(branchCode + randomPart);
        } while (accountRepository.existsByAccountNumber(accountNumber.toString()));

        return accountNumber;
    }
}

package com.OBS.onlinebankingsystem.service;

import org.springframework.stereotype.Service;

import com.OBS.onlinebankingsystem.model.Account;
import com.OBS.onlinebankingsystem.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Double getBalanceByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            return (Double) account.getBalance();
        } else {
            throw new IllegalArgumentException("Account not found with number: " + accountNumber);
        }
    }
}

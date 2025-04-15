package com.OBS.onlinebankingsystem.dao;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.OBS.onlinebankingsystem.model.Account;
import com.OBS.onlinebankingsystem.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountDao {

    private final AccountRepository accountRepository;

    /**
     * Creates a new account and saves it to the database.
     *
     * @param account The account to be created.
     * @return The saved account.
     */
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    /**
     * Retrieves an account by its account number.
     *
     * @param number The account number to search for.
     * @return An Optional containing the account if found, or empty if not found.
     */
    public Optional<Account> getAccountByNumber(String number) {
        return Optional.ofNullable(accountRepository.findByAccountNumber(number));
    }
}


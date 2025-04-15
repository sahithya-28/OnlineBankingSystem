package com.OBS.onlinebankingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OBS.onlinebankingsystem.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // Custom query method to find an account by its account number
    Account findByAccountNumber(String accountNumber);
}
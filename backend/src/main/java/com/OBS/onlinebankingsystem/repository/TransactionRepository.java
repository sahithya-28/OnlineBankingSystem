package com.OBS.onlinebankingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OBS.onlinebankingsystem.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromAccountIdOrToAccountId(Long fromAccountId, Long toAccountId);
}
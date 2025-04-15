package com.OBS.onlinebankingsystem.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.OBS.onlinebankingsystem.model.Transaction;
import com.OBS.onlinebankingsystem.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionDao {
    private final TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsForAccount(Long accountId) {
        // Fetch transactions where the account is either the sender or the receiver
        return transactionRepository.findByFromAccountIdOrToAccountId(accountId, accountId);
    }
}

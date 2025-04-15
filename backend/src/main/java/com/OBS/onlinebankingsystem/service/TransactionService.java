package com.OBS.onlinebankingsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.OBS.onlinebankingsystem.model.Account;
import com.OBS.onlinebankingsystem.model.Transaction;
import com.OBS.onlinebankingsystem.repository.AccountRepository;
import com.OBS.onlinebankingsystem.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    // 🆕 Updated method with PIN verification
    public String transfer(String fromAccountString, String toAccountString, double amount, String pin) {
        Account from = accountRepository.findByAccountNumber(fromAccountString);
        Account to = accountRepository.findByAccountNumber(toAccountString);

        if (from == null || to == null) {
            return "Invalid account";
        }

        if (!from.getPin().equals(pin)) {
            return "Invalid PIN. Transaction denied.";
        }

        if (from.getBalance() < amount) {
            return "Insufficient balance.";
        }

        // Deduct and update balances
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        accountRepository.save(from);
        accountRepository.save(to);

        Transaction transaction = new Transaction(from, to, amount);
        transactionRepository.save(transaction);

        return "Transfer successful from " + fromAccountString + " to " + toAccountString + " of amount " + amount;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}

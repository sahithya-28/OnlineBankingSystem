package com.OBS.onlinebankingsystem.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.OBS.onlinebankingsystem.model.Transaction;
import com.OBS.onlinebankingsystem.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
public ResponseEntity<String> transferFunds(
        @RequestParam String fromAccount,
        @RequestParam String toAccount,
        @RequestParam double amount,
        @RequestParam String pin) {
    return ResponseEntity.ok(transactionService.transfer(fromAccount, toAccount, amount, pin));
}


    @GetMapping
    public List<Transaction> showAllTransactions() {
        return transactionService.getAllTransactions();
    }
}

package com.OBS.onlinebankingsystem.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OBS.onlinebankingsystem.model.Account;
import com.OBS.onlinebankingsystem.model.Tax;
import com.OBS.onlinebankingsystem.repository.AccountRepository;
import com.OBS.onlinebankingsystem.repository.TaxRepository;

@Service
public class TaxService {

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private AccountRepository accountRepository;

    // Calculates tax based on the given minimum balance
    public BigDecimal calculateTax(BigDecimal minBalance) {
        if (minBalance.compareTo(new BigDecimal("5000")) < 0) {
            return new BigDecimal("100");
        }

        int multiplier = minBalance
                .divide(new BigDecimal("5000"), RoundingMode.FLOOR)
                .intValue();

        return new BigDecimal("100").multiply(BigDecimal.valueOf(Math.pow(2, multiplier)));
    }

    // Applies tax to a specific account
    public void applyTaxToAccount(Account account) {
        BigDecimal minBalance = account.getMinBalance();
        BigDecimal taxAmount = calculateTax(minBalance);

        Tax tax = new Tax();
        tax.setAccount(account);
        tax.setMinBalance(minBalance);
        tax.setTaxAmount(taxAmount);

        taxRepository.save(tax);
    }

    // Optionally apply tax to all accounts (for monthly scheduler or admin trigger)
    public void applyTaxToAllAccounts() {
        List<Account> allAccounts = accountRepository.findAll();
        for (Account account : allAccounts) {
            applyTaxToAccount(account);
        }
    }

    // Get tax records for a specific account
    public List<Tax> getTaxHistoryByAccountId(int accountId) {
        return taxRepository.findByAccountId(accountId);
    }
}


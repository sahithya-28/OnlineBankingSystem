package com.OBS.onlinebankingsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OBS.onlinebankingsystem.dao.AccountDao;
import com.OBS.onlinebankingsystem.dto.EServiceRequest;
import com.OBS.onlinebankingsystem.model.Account;
import com.OBS.onlinebankingsystem.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    
    private final AccountDao accountDao;
    private final AccountRepository accountRepository;

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountDao.createAccount(account);
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<Account> getAccount(@PathVariable String number) {
        return accountDao.getAccountByNumber(number)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/with-minimum-balance")
    public ResponseEntity<?> createAccountWithMinimumBalance(@RequestBody Account account) {
        if (account.getBalance() == null || account.getMinimumBalance() == null) {
            return ResponseEntity.badRequest().body("Balance and Minimum Balance must be provided.");
        }

        if (account.getBalance() < account.getMinimumBalance()) {
            return ResponseEntity.badRequest().body("Account balance must be at least equal to the minimum balance.");
        }

        return ResponseEntity.ok(accountDao.createAccount(account));
    }

    // ✅ Check balance with PIN verification
    @GetMapping("/balance")
    public ResponseEntity<String> checkBalance(@RequestParam String accountNumber, @RequestParam String pin) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        
        if (account == null) {
            return ResponseEntity.badRequest().body("Account not found.");
        }

        if (!account.getPin().equals(pin)) {
            return ResponseEntity.status(403).body("Invalid PIN.");
        }

        return ResponseEntity.ok("Available Balance: ₹" + account.getBalance());
    }

    @PostMapping("/e-services")
    public ResponseEntity<String> handleEService(@RequestBody EServiceRequest request) {
        Account account = accountRepository.findByAccountNumber(request.getAccountNumber());
    
        if (account == null) {
            return ResponseEntity.badRequest().body("Account not found.");
        }
    
        if (!account.getPin().equals(request.getPin())) {
            return ResponseEntity.status(403).body("Invalid PIN.");
        }
    
        return switch (request.getServiceType().toUpperCase()) {
            case "ATM_CARD" -> {
                account.setAtmCardApplied(true);
                accountRepository.save(account);
                yield ResponseEntity.ok("ATM Card application submitted.");
            }
            case "BLOCK_ATM" -> {
                if (request.getReason() == null || request.getReason().isEmpty()) {
                    yield ResponseEntity.badRequest().body("Reason is required to block ATM card.");
                }
                account.setAtmCardApplied(false);
                // You can log the reason here or save it to a future table
                accountRepository.save(account);
                yield ResponseEntity.ok("ATM Card blocked.");
            }
            case "CREDIT_CARD" -> {
                account.setCreditCardApplied(true);
                accountRepository.save(account);
                yield ResponseEntity.ok("Credit card application submitted.");
            }
            case "BLOCK_CREDIT_CARD" -> {
                if (request.getReason() == null || request.getReason().isEmpty()) {
                    yield ResponseEntity.badRequest().body("Reason is required to block credit card.");
                }
                account.setCreditCardApplied(false);
                // Again, log reason if needed
                accountRepository.save(account);
                yield ResponseEntity.ok("Credit card blocked.");
            }
            default -> ResponseEntity.badRequest().body("Invalid service type.");
        };
    
    }
    public class BillPaymentController {
        @PostMapping("/pay-bills")
        public String payBill() {
            return "Bill payment feature coming soon.";
        }
    }
}

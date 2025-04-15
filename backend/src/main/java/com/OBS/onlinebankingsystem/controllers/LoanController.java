package com.OBS.onlinebankingsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.OBS.onlinebankingsystem.model.Loan;
import com.OBS.onlinebankingsystem.repository.LoanRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanRepository loanRepository;

    @PostMapping("/apply")
    public ResponseEntity<String> applyLoan(@RequestBody Loan loan) {
        if (loan.getAccountNumber() == null || loan.getAadharNumber() == null ||
            loan.getAnnualIncome() == null || loan.getLoanDuration() == null || loan.getLoanType() == null) {
            return ResponseEntity.badRequest().body("All fields are required.");
        }

        loanRepository.save(loan);
        return ResponseEntity.ok("Loan application submitted successfully.");
    }
}

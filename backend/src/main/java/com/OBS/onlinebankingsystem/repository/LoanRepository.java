package com.OBS.onlinebankingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OBS.onlinebankingsystem.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}

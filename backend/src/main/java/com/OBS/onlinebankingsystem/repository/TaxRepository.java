package com.OBS.onlinebankingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OBS.onlinebankingsystem.model.Tax;

public interface TaxRepository extends JpaRepository<Tax, Integer> {
    List<Tax> findByAccountId(int accountId);
}


package com.OBS.onlinebankingsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private String accountType;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    private Double minimumBalance;

    @Column(nullable = false)
    private String pin;

    @Column(nullable = false)
    private boolean atmCardApplied = false;

    @Column(nullable = false)
    private boolean creditCardApplied = false;

    // Explicit getter for PIN if needed
    public String getPin() {
        return this.pin;
    }

    public double getMinBalance() {
        return this.minimumBalance;
    }

    // ✅ Getter for balance
    public Double getBalance() {
        return this.balance;
    }

    // ✅ Setter for balance
    public void setBalance(Double balance) {
        this.balance = balance;
    }

}

package com.OBS.onlinebankingsystem.dto;

import lombok.Data;

@Data
public class EServiceRequest {
    private String accountNumber;
    private String serviceType; // e.g., "ATM_CARD", "BLOCK_ATM", "CHECKBOOK", "CREDIT_CARD"
    private String pin;
    private String reason; // Only required for blocking services
}

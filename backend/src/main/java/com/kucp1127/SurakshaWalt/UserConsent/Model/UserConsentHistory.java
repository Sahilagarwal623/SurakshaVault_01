package com.kucp1127.SurakshaWalt.UserConsent.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserConsentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    private String username;
    private String companyName;
    private Boolean showAdhaarNumber;
    private Boolean showPanNumber;
    private long salaryEncryptedBits;
    private int cibilEncryptedBits;
    private Boolean showTransactions;
    private Boolean showAccountNumber;
    private Boolean showCardNumber;

    private LocalDateTime changedAt;
}
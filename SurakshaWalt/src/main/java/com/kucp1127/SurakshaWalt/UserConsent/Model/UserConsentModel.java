package com.kucp1127.SurakshaWalt.UserConsent.Model;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserConsentModel {
    @EmbeddedId
    private UserConsentId id;
    private Boolean showAdhaarNumber;
    private Boolean showPanNumber;
    private long salaryEncryptedBits;
    private int cibilEncryptedBits;
    private Boolean showTransactions;
    private Boolean showAccountNumber;
    private Boolean showCardNumber;
    private List<String> locations;
    private LocalDateTime localDateTime;
}
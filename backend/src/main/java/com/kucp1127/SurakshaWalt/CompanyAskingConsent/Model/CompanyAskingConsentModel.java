package com.kucp1127.SurakshaWalt.CompanyAskingConsent.Model;

import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyAskingConsentModel {

    @Id
    private String companyName;

    private Boolean isAskingAdhaarNumber;
    private Boolean isAskingPanNumber;
    private Boolean isAskingAnnualIncome;
    private Boolean isAskingAccountNumber;
    private Boolean isAskingCardNumber;
    private Boolean isAskingCibilScore;
    private Boolean isAskingTransactions;

    private String purpose;

    private Boolean isAskingEmail;
    private Boolean isAskingPhoneNumber;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

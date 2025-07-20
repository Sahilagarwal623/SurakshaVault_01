package com.kucp1127.SurakshaWalt.UserConsent.Model;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserConsentStatus {
    @EmbeddedId
    private UserConsentId id;
    private Boolean revoked;
    private Boolean blocked;
    private Boolean accepted;
}

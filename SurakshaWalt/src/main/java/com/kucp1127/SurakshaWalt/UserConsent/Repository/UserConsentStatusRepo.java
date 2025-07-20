package com.kucp1127.SurakshaWalt.UserConsent.Repository;

import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentId;
import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConsentStatusRepo extends JpaRepository<UserConsentStatus, UserConsentId> {
}

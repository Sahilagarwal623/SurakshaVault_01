package com.kucp1127.SurakshaWalt.UserConsent.Repository;

import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserConsentHistoryRepository extends JpaRepository<UserConsentHistory, Long> {
    List<UserConsentHistory> findByUsernameAndCompanyNameOrderByChangedAtDesc(
            String username, String companyName);
}
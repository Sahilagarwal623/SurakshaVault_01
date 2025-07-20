package com.kucp1127.SurakshaWalt.CompanyAskingConsent.Repository;

import com.kucp1127.SurakshaWalt.CompanyAskingConsent.Model.CompanyAskingConsentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyAskingConsentRepository
        extends JpaRepository<CompanyAskingConsentModel, String> {
    // String is the type of companyName (the @Id field)
}

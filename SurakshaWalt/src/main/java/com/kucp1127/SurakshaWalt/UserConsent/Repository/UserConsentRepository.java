package com.kucp1127.SurakshaWalt.UserConsent.Repository;

import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentId;
import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserConsentRepository extends JpaRepository<UserConsentModel, UserConsentId>{
    List<UserConsentModel> findByIdUsername(String username);
    @Query("SELECT u FROM UserConsentModel u WHERE u.id.companyName = :companyName")
    List<UserConsentModel> findForCompany(@Param("companyName") String companyName);

}

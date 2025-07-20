package com.kucp1127.SurakshaWalt.UserConsent.Service;

import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentHistory;
import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentId;
import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentModel;
import com.kucp1127.SurakshaWalt.UserConsent.Repository.UserConsentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserConsentHistoryService {


    @Autowired
    private UserConsentHistoryRepository historyRepo;


    public UserConsentHistory recordHistory(UserConsentModel consentModel) {
        UserConsentHistory snapshot = new UserConsentHistory();
        UserConsentId originalId = consentModel.getId();
        snapshot.setUsername(originalId.getUsername());
        snapshot.setCompanyName(originalId.getCompanyName());
        snapshot.setShowAdhaarNumber(consentModel.getShowAdhaarNumber());
        snapshot.setShowPanNumber(consentModel.getShowPanNumber());
        snapshot.setSalaryEncryptedBits(consentModel.getSalaryEncryptedBits());
        snapshot.setCibilEncryptedBits(consentModel.getCibilEncryptedBits());
        snapshot.setShowTransactions(consentModel.getShowTransactions());
        snapshot.setChangedAt(LocalDateTime.now());
        return historyRepo.save(snapshot);
    }

    public List<UserConsentHistory> getHistory(String username, String companyName) {
        return historyRepo.findByUsernameAndCompanyNameOrderByChangedAtDesc(
                username, companyName
        );
    }
}

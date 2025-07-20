package com.kucp1127.SurakshaWalt.UserConsent.Service;

import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentId;
import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentModel;
import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentStatus;
import com.kucp1127.SurakshaWalt.UserConsent.Repository.UserConsentRepository;
import com.kucp1127.SurakshaWalt.UserConsent.Repository.UserConsentStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserConsentService {

    @Autowired
    private UserConsentRepository repository;

    @Autowired
    private UserConsentHistoryService userConsentHistoryService;

    @Autowired
    private UserConsentStatusRepo userConsentStatusRepo;

    public List<UserConsentModel> getAllConsentsForUser(String username) {
        return repository.findByIdUsername(username);
    }

    public List<UserConsentModel> getAllConsentsForCompany(String companyName) {
        return repository.findForCompany(companyName);
    }

    public UserConsentModel saveConsent(UserConsentModel consent) {
        userConsentHistoryService.recordHistory(consent);
        Optional<UserConsentStatus> userConsentStatus = userConsentStatusRepo.findById(consent.getId());
        if(userConsentStatus.isPresent()){
            userConsentStatus.get().setRevoked(false);
            userConsentStatus.get().setAccepted(true);
            userConsentStatus.get().setBlocked(false);
            userConsentStatusRepo.save(userConsentStatus.get());
        }
        else{
            UserConsentStatus userConsentStatus1 = new UserConsentStatus();
            userConsentStatus1.setId(consent.getId());
            userConsentStatus1.setRevoked(false);
            userConsentStatus1.setAccepted(true);
            userConsentStatus1.setBlocked(false);
            userConsentStatusRepo.save(userConsentStatus1);
        }
        return repository.save(consent);
    }

    public Optional<UserConsentModel> getConsent(UserConsentId id) {
        return repository.findById(id);
    }

    public Optional<UserConsentModel> updateConsent(UserConsentId id, UserConsentModel updated) {
        userConsentHistoryService.recordHistory(updated);
        Optional<UserConsentStatus> userConsentStatus = userConsentStatusRepo.findById(id);
        if(userConsentStatus.isPresent()){
            userConsentStatus.get().setRevoked(false);
            userConsentStatus.get().setAccepted(true);
            userConsentStatus.get().setBlocked(false);
            userConsentStatusRepo.save(userConsentStatus.get());
        }
        return repository.findById(id)
            .map(existing -> {
                existing.setShowAdhaarNumber(updated.getShowAdhaarNumber());
                existing.setShowPanNumber(updated.getShowPanNumber());
                existing.setSalaryEncryptedBits(updated.getSalaryEncryptedBits());
                existing.setCibilEncryptedBits(updated.getCibilEncryptedBits());
                existing.setShowTransactions(updated.getShowTransactions());
                existing.setShowAccountNumber(updated.getShowAccountNumber());
                existing.setShowCardNumber(updated.getShowCardNumber());
                existing.setLocations(updated.getLocations());
                existing.setLocalDateTime(updated.getLocalDateTime());
                return repository.save(existing);
            });
    }

    public Optional<UserConsentModel> revokeConsent(UserConsentId id) {

        Optional<UserConsentModel> userConsentModel = repository.findById(id);

        if(userConsentModel.isPresent()){
            UserConsentModel existing = userConsentModel.get();
            existing.setShowAdhaarNumber(false);
            existing.setShowPanNumber(false);
            existing.setSalaryEncryptedBits(10000);
            existing.setCibilEncryptedBits(100);
            existing.setShowTransactions(false);
            existing.setShowAccountNumber(false);
            existing.setShowCardNumber(false);
            existing.setLocations(new ArrayList<>());
            existing.setLocalDateTime(LocalDateTime.now());
            userConsentHistoryService.recordHistory(existing);
        }

        return repository.findById(id)
                .map(existing -> {
                    existing.setShowAdhaarNumber(false);
                    existing.setShowPanNumber(false);
                    existing.setSalaryEncryptedBits(10000);
                    existing.setCibilEncryptedBits(100);
                    existing.setShowTransactions(false);
                    existing.setShowAccountNumber(false);
                    existing.setShowCardNumber(false);
                    existing.setLocations(new ArrayList<>());
                    existing.setLocalDateTime(LocalDateTime.now());
                    return repository.save(existing);
                });
    }
}

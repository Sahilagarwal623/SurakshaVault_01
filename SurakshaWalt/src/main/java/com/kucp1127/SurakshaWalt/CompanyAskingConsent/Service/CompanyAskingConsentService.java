package com.kucp1127.SurakshaWalt.CompanyAskingConsent.Service;

import com.kucp1127.SurakshaWalt.CompanyAskingConsent.Model.CompanyAskingConsentModel;
import com.kucp1127.SurakshaWalt.CompanyAskingConsent.Repository.CompanyAskingConsentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyAskingConsentService {

    private final CompanyAskingConsentRepository repository;

    @Autowired
    public CompanyAskingConsentService(CompanyAskingConsentRepository repository) {
        this.repository = repository;
    }

    /**
     * Save or update a consent model.
     */
    public CompanyAskingConsentModel save(CompanyAskingConsentModel model) {
        return repository.save(model);
    }

    /**
     * Find a consent by companyName.
     */
    public Optional<CompanyAskingConsentModel> findByCompanyName(String companyName) {
        return repository.findById(companyName);
    }

    /**
     * Delete a consent by companyName.
     */
    public void deleteByCompanyName(String companyName) {
        repository.deleteById(companyName);
    }

    public List<CompanyAskingConsentModel> findAll() {
        return repository.findAll();
    }
}

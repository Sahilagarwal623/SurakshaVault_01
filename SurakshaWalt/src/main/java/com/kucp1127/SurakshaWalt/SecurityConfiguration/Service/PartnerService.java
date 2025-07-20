package com.kucp1127.SurakshaWalt.SecurityConfiguration.Service;


import com.kucp1127.SurakshaWalt.SecurityConfiguration.Model.PartnerRegistration;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.Repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    public PartnerRegistration registerPartner(PartnerRegistration partnerRegistration) {
        return partnerRepository.save(partnerRegistration);
    }

    public Optional<PartnerRegistration> findByUsername(String username) {
        return partnerRepository.findById(username);
    }

    public Object getAllUsernames() {
        return partnerRepository.findAll();
    }

    public Object updateRegistration(String username) {
        Optional<PartnerRegistration> partnerRegistration = partnerRepository.findById(username);
        if(partnerRegistration.isPresent()){
            partnerRegistration.get().setIsRegistration(true);
            return partnerRepository.save(partnerRegistration.get());
        }
        return null;
    }

    public Object findAllCompanies() {
        List<PartnerRegistration> partnerRegistrationList = partnerRepository.findAll() , partnerRegistrations = new ArrayList<>();
        for(PartnerRegistration partnerRegistration : partnerRegistrationList){
            if(partnerRegistration.getIsRegistration()
            !=null && partnerRegistration.getIsRegistration()){
                partnerRegistrations.add(partnerRegistration);
            }
        }
        return partnerRegistrations;
    }
}

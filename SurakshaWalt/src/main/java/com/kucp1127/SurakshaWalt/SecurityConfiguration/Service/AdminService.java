package com.kucp1127.SurakshaWalt.SecurityConfiguration.Service;

import com.kucp1127.SurakshaWalt.SecurityConfiguration.Model.AdminRegistration;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.Model.PartnerRegistration;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.Model.UserRegistration;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public AdminRegistration registerAdmin(AdminRegistration adminRegistration) {
        return adminRepository.save(adminRegistration);
    }

    public Optional<AdminRegistration> findByUsername(String username) {
        return adminRepository.findById(username);
    }
}

package com.kucp1127.SurakshaWalt.SecurityConfiguration.Configuration;

import com.kucp1127.SurakshaWalt.SecurityConfiguration.Model.AdminRegistration;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.Model.PartnerRegistration;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.Model.UserRegistration;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.Service.AdminService;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.Service.PartnerService;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private AdminService adminService;

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 1) Check User
        Optional<UserRegistration> userRegistration = userService.findByUsername(username);
        if (userRegistration.isPresent()) {
            return new UserPrincipal(userRegistration.get());
        }

        // 2) Check Partner
        Optional<PartnerRegistration> partnerRegistration = partnerService.findByUsername(username);
        if (partnerRegistration.isPresent()) {
            return new PartnerPrincipal(partnerRegistration.get());
        }

        // 3) check admin
        Optional<AdminRegistration> adminRegistration = adminService.findByUsername(username);
        if (adminRegistration.isPresent()) {
            return new AdminPrincipal(adminRegistration.get());
        }

        // 4) If neither found
        System.out.println("TRhe account doesn't exists.............");
        throw new UsernameNotFoundException("User 404");
    }

}
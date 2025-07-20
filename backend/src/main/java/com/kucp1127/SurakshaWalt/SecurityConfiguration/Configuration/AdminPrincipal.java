package com.kucp1127.SurakshaWalt.SecurityConfiguration.Configuration;

import com.kucp1127.SurakshaWalt.SecurityConfiguration.Model.AdminRegistration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AdminPrincipal implements UserDetails {

    private final AdminRegistration adminRegistration;

    public AdminPrincipal(AdminRegistration users){
        this.adminRegistration=users;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String getPassword() {
        return adminRegistration.getPassKey();
    }

    @Override
    public String getUsername() {
        return adminRegistration.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

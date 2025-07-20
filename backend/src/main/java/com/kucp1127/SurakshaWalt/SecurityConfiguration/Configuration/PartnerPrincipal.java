package com.kucp1127.SurakshaWalt.SecurityConfiguration.Configuration;

import com.kucp1127.SurakshaWalt.SecurityConfiguration.Model.PartnerRegistration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class PartnerPrincipal implements UserDetails {

    private final PartnerRegistration partnerRegistration;

    public PartnerPrincipal(PartnerRegistration users){
        this.partnerRegistration=users;
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
        return partnerRegistration.getPassword();
    }

    @Override
    public String getUsername() {
        return partnerRegistration.getUsername();
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

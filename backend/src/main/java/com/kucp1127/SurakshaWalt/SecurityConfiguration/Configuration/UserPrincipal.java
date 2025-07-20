package com.kucp1127.SurakshaWalt.SecurityConfiguration.Configuration;

import com.kucp1127.SurakshaWalt.SecurityConfiguration.Model.UserRegistration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class UserPrincipal implements UserDetails {

    private final UserRegistration userRegistration;

    public UserPrincipal(UserRegistration users){
        this.userRegistration=users;
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
        return userRegistration.getPassword();
    }

    @Override
    public String getUsername() {
        return userRegistration.getUsername();
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
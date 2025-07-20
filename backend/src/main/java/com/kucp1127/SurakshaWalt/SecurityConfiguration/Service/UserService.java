package com.kucp1127.SurakshaWalt.SecurityConfiguration.Service;

import com.kucp1127.SurakshaWalt.SecurityConfiguration.Model.UserRegistration;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.Repository.UserRepository;
import com.kucp1127.SurakshaWalt.UserInformation.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoService userInfoService;

    public UserRegistration registerUser(UserRegistration userRegistration) {
        userInfoService.createUserInfo(userRegistration.getUsername());
        return userRepository.save(userRegistration);
    }

    public Optional<UserRegistration> findByUsername(String username) {
        return userRepository.findById(username);
    }
}

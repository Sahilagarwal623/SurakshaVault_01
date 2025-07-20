package com.kucp1127.SurakshaWalt.UserInformation.Service;

import com.kucp1127.SurakshaWalt.UserInformation.Model.UserInfoModel;
import com.kucp1127.SurakshaWalt.UserInformation.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserInfoService {


    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfoModel createUserInfo(String username) {
        UserInfoModel userInfo = new UserInfoModel();
        userInfo.setUsername(username);
        userInfo.setAdhaarNumber(null);
        userInfo.setPanNumber(null);
        userInfo.setAnnualIncome("");
        userInfo.setCibilScore("");
        userInfo.setAccountNumber(null);
        userInfo.setCardNumber(null);
        userInfo.setTransactions(new ArrayList<>());
        return userInfoRepository.save(userInfo);
    }


    public Optional<UserInfoModel> updateUserInfo(String username, UserInfoModel updatedInfo) {
        return userInfoRepository.findById(username)
            .map(existing -> {
                existing.setAdhaarNumber(updatedInfo.getAdhaarNumber());
                existing.setPanNumber(updatedInfo.getPanNumber());
                existing.setAnnualIncome(updatedInfo.getAnnualIncome());
                existing.setCibilScore(updatedInfo.getCibilScore());
                existing.setTransactions(updatedInfo.getTransactions());
                existing.setAccountNumber(updatedInfo.getAccountNumber());
                existing.setCardNumber(updatedInfo.getCardNumber());
                return userInfoRepository.save(existing);
            });
    }
    public boolean deleteUserInfo(String username) {
        if (userInfoRepository.existsById(username)) {
            userInfoRepository.deleteById(username);
            return true;
        }
        return false;
    }

    public Object findById(String username) {
        return userInfoRepository.findById(username);
    }

    public Optional<UserInfoModel> giveById(String username){
        return userInfoRepository.findById(username);
    }
}
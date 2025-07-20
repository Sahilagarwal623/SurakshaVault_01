package com.kucp1127.SurakshaWalt.UserSendingData.Service;


import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentId;
import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentModel;
import com.kucp1127.SurakshaWalt.UserConsent.Service.UserConsentService;
import com.kucp1127.SurakshaWalt.UserInformation.Model.UserInfoModel;
import com.kucp1127.SurakshaWalt.UserInformation.Service.UserInfoService;
import com.kucp1127.SurakshaWalt.UserSendingData.DTO.UserDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSendingDataService {

    @Autowired
    private UserConsentService userConsentService;

    @Autowired
    private UserInfoService userInfoService;

    public static String maskEnd(String input, long count) {
        if (input == null) {
            return null;
        }
        int len = input.length();
        if (count >= len) {
            return "*".repeat(len);
        }
        int unmaskedLen = len - (int) count;
        return input.substring(0, unmaskedLen)
                + "*".repeat((int) count);
    }

    public static String maskEnd(String input, int count) {
        return maskEnd(input, (long) count);
    }

    public UserInfoModel getUserData(UserDataDTO userDataDTO){
        Optional<UserConsentModel> userConsentModel = userConsentService.getConsent(new UserConsentId(userDataDTO.getUsername(),userDataDTO.getCompanyName()));
        if(userConsentModel.isPresent()){
            Optional<UserInfoModel> userInfoModel1 = userInfoService.giveById(userDataDTO.getUsername());
            UserInfoModel userInfoModel = new UserInfoModel();
            if(userInfoModel1.isPresent()){
                userInfoModel.setUsername(userDataDTO.getUsername());
                if(userConsentModel.get().getShowAccountNumber()) userInfoModel.setAccountNumber(userInfoModel1.get().getAccountNumber());
                if(userConsentModel.get().getShowCardNumber()) userInfoModel.setCardNumber(userInfoModel1.get().getCardNumber());
                if(userConsentModel.get().getShowAdhaarNumber()) userInfoModel.setAdhaarNumber(userInfoModel1.get().getAdhaarNumber());
                if(userConsentModel.get().getShowPanNumber()) userInfoModel.setPanNumber(userInfoModel1.get().getPanNumber());
                int x = userConsentModel.get().getCibilEncryptedBits();
                userInfoModel.setCibilScore(maskEnd(userInfoModel1.get().getCibilScore(),x));

                if(userConsentModel.get().getShowTransactions()) userInfoModel.setTransactions(userInfoModel1.get().getTransactions());
                long y = userConsentModel.get().getSalaryEncryptedBits();
                userInfoModel.setAnnualIncome(maskEnd(userInfoModel1.get().getAnnualIncome(),y));
            }
            return userInfoModel;
        }
        return null;
    }

}

package com.kucp1127.SurakshaWalt.UserSendingData.Controller;


import com.kucp1127.SurakshaWalt.UserSendingData.DTO.UserDataDTO;
import com.kucp1127.SurakshaWalt.UserSendingData.Service.UserSendingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class UserSendingDataController {

    @Autowired
    private UserSendingDataService userSendingDataService;

    @PostMapping("/getData")
    public ResponseEntity<?> getUserData(@RequestBody UserDataDTO userDataDTO){
        return ResponseEntity.ok(userSendingDataService.getUserData(userDataDTO));
    }

}

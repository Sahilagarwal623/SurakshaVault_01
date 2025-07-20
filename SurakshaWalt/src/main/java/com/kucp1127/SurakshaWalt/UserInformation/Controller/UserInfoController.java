package com.kucp1127.SurakshaWalt.UserInformation.Controller;

import com.kucp1127.SurakshaWalt.UserInformation.Model.UserInfoModel;
import com.kucp1127.SurakshaWalt.UserInformation.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/userinfo")
@CrossOrigin("*")
public class UserInfoController {

    @Autowired
    private  UserInfoService userInfoService;

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUserInfo(
            @PathVariable String username,
            @RequestBody UserInfoModel updatedInfo
    ) {
        Optional<UserInfoModel> result = userInfoService.updateUserInfo(username, updatedInfo);
        return result
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserInfo(@PathVariable String username){
        return ResponseEntity.ok(userInfoService.findById(username));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUserInfo(@PathVariable String username) {
        boolean deleted = userInfoService.deleteUserInfo(username);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

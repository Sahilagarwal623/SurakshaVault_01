package com.kucp1127.SurakshaWalt.UserConsent.Controller;

import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentHistory;
import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentId;
import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentModel;
import com.kucp1127.SurakshaWalt.UserConsent.Model.UserConsentStatus;
import com.kucp1127.SurakshaWalt.UserConsent.Repository.UserConsentStatusRepo;
import com.kucp1127.SurakshaWalt.UserConsent.Service.UserConsentHistoryService;
import com.kucp1127.SurakshaWalt.UserConsent.Service.UserConsentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-consent")
@CrossOrigin("*")
public class UserConsentController {

    @Autowired
    private  UserConsentService service;

    @Autowired
    private UserConsentHistoryService userConsentHistoryService;

    @Autowired
    private UserConsentStatusRepo userConsentStatusRepo;

    @PostMapping
    public ResponseEntity<UserConsentModel> createConsent(@RequestBody UserConsentModel consent) {
        UserConsentModel saved = service.saveConsent(consent);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/getStatus")
    public ResponseEntity<?> getStatus(
            @RequestParam String username,
            @RequestParam String companyName
    ){
        UserConsentId id = new UserConsentId(username, companyName);
        Optional<UserConsentStatus> userConsentStatus = userConsentStatusRepo.findById(id);
        if(userConsentStatus.isPresent()){
            return ResponseEntity.ok(userConsentStatus.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<UserConsentModel>> getAllConsentsForUser(
            @PathVariable String username
    ) {
        List<UserConsentModel> list = service.getAllConsentsForUser(username);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/company/{companyName}")
    public ResponseEntity<List<UserConsentModel>> getAllConsentsForCompany(
            @PathVariable String companyName
    ) {
        List<UserConsentModel> list = service.getAllConsentsForCompany(companyName);
        return ResponseEntity.ok(list);
    }

    @GetMapping
    public ResponseEntity<?> getConsent(
        @RequestParam String username,
        @RequestParam String companyName
    ) {
        UserConsentId id = new UserConsentId(username, companyName);
        Optional<UserConsentModel> result = service.getConsent(id);
        return result.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<?> updateConsent(@RequestBody UserConsentModel consent) {
        UserConsentId id = consent.getId();
        Optional<UserConsentModel> result = service.updateConsent(id, consent);
        return result.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/revoke")
    public ResponseEntity<?> revokeConsent(
        @RequestParam String username,
        @RequestParam String companyName
    ) {
        UserConsentId id = new UserConsentId(username, companyName);
        Optional<UserConsentModel> revoked = service.revokeConsent(id);
        if (revoked.isPresent()) {
            Optional<UserConsentStatus> userConsentStatus = userConsentStatusRepo.findById(id);
            if(userConsentStatus.isPresent()){
                userConsentStatus.get().setRevoked(true);
                userConsentStatus.get().setAccepted(false);
                userConsentStatus.get().setBlocked(false);
                userConsentStatusRepo.save(userConsentStatus.get());
            }
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/block")
    public ResponseEntity<?> blockConsent(
            @RequestParam String username,
            @RequestParam String companyName
    ){
        UserConsentId id = new UserConsentId(username, companyName);
        Optional<UserConsentModel> revoked = service.revokeConsent(id);
        if (revoked.isPresent()) {
            Optional<UserConsentStatus> userConsentStatus = userConsentStatusRepo.findById(id);
            if(userConsentStatus.isPresent()){
                userConsentStatus.get().setRevoked(false);
                userConsentStatus.get().setAccepted(false);
                userConsentStatus.get().setBlocked(true);
                userConsentStatusRepo.save(userConsentStatus.get());
            }
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/history")
    public ResponseEntity<List<UserConsentHistory>> getHistory(
            @RequestParam String username,
            @RequestParam String companyName
    ) {
        List<UserConsentHistory> list = userConsentHistoryService.getHistory(username, companyName);
        return ResponseEntity.ok(list);
    }

}
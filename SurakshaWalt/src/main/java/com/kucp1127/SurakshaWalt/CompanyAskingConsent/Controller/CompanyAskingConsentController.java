package com.kucp1127.SurakshaWalt.CompanyAskingConsent.Controller;

import com.kucp1127.SurakshaWalt.CompanyAskingConsent.Model.CompanyAskingConsentModel;
import com.kucp1127.SurakshaWalt.CompanyAskingConsent.Service.CompanyAskingConsentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consents")
@CrossOrigin("*")
public class CompanyAskingConsentController {

    private final CompanyAskingConsentService service;

    @Autowired
    public CompanyAskingConsentController(CompanyAskingConsentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CompanyAskingConsentModel> create(
            @RequestBody CompanyAskingConsentModel model) {
        CompanyAskingConsentModel saved = service.save(model);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<CompanyAskingConsentModel>> getAll() {
        List<CompanyAskingConsentModel> all = service.findAll(); // or add a findAll() method in service
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{companyName}")
    public ResponseEntity<CompanyAskingConsentModel> getOne(
            @PathVariable String companyName) {
        return service.findByCompanyName(companyName)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{companyName}")
    public ResponseEntity<Void> delete(
            @PathVariable String companyName) {
        service.deleteByCompanyName(companyName);
        return ResponseEntity.noContent().build();
    }
}

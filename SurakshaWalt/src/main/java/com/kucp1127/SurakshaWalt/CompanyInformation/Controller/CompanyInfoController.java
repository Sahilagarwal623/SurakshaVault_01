package com.kucp1127.SurakshaWalt.CompanyInformation.Controller;

import com.kucp1127.SurakshaWalt.CompanyInformation.Model.CompanyInfoModel;
import com.kucp1127.SurakshaWalt.CompanyInformation.Service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@CrossOrigin("*")
public class CompanyInfoController {

    @Autowired
    private CompanyInfoService companyInfoservice;

    @GetMapping
    public ResponseEntity<List<CompanyInfoModel>> list() {
        return ResponseEntity.ok(companyInfoservice.getAllCompanies());
    }

    @GetMapping("/{companyName}")
    public ResponseEntity<CompanyInfoModel> getOne(@PathVariable String companyName) {
        return companyInfoservice.getCompany(companyName)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<CompanyInfoModel> upsertCompany(
            @RequestBody CompanyInfoModel model
    ) {
        CompanyInfoModel saved = companyInfoservice.updateCompany(model);
        return ResponseEntity.ok(saved);
    }
}

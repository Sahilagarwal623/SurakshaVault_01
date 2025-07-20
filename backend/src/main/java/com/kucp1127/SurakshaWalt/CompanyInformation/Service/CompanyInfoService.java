package com.kucp1127.SurakshaWalt.CompanyInformation.Service;

import com.kucp1127.SurakshaWalt.CompanyInformation.Model.CompanyInfoModel;
import com.kucp1127.SurakshaWalt.CompanyInformation.Repository.CompanyInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyInfoService {

    @Autowired
    private CompanyInfoRepo repo;

    public List<CompanyInfoModel> getAllCompanies() {
        return repo.findAll();
    }


    public Optional<CompanyInfoModel> getCompany(String companyName) {
        return repo.findById(companyName);
    }

    @Transactional
    public CompanyInfoModel updateCompany(CompanyInfoModel model) {
        return repo.save(model);
    }
}

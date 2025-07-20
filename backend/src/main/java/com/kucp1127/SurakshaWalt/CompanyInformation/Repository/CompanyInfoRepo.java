package com.kucp1127.SurakshaWalt.CompanyInformation.Repository;

import com.kucp1127.SurakshaWalt.CompanyInformation.Model.CompanyInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyInfoRepo extends JpaRepository<CompanyInfoModel, String> {

}

package com.kucp1127.SurakshaWalt.CompanyInformation.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CompanyInfoModel {
    @Id
    private String companyName;
    private String gstNumber;
    private String description;
    private String address;

    // new fields
    private String contactEmail;
    private String contactPhone;
    private String website;
    private String industry;
    private String cinId;
    private String dinId;
}

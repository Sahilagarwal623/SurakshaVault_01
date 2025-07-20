package com.kucp1127.SurakshaWalt.SecurityConfiguration.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PartnerRegistration {
    @Id
    private String username;
    private String email;
    private int companyAge;
    private String password;
    private Boolean isRegistration;
}

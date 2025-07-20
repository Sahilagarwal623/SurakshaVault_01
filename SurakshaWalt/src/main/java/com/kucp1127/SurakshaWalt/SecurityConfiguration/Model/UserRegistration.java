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
public class UserRegistration {
    @Id
    private String username;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;
    private int age;
}

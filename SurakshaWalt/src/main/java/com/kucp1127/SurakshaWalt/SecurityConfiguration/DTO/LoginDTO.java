package com.kucp1127.SurakshaWalt.SecurityConfiguration.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {
    private String username;
    private String password;
}

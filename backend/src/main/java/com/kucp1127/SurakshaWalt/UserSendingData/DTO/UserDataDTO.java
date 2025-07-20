package com.kucp1127.SurakshaWalt.UserSendingData.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDTO {

    private String username;
    private String companyName;
    private String location;
    private LocalDateTime localDateTime;

}

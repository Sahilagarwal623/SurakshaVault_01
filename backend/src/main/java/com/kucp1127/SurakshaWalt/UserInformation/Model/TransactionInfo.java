package com.kucp1127.SurakshaWalt.UserInformation.Model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInfo {
    private String fromAccount;
    private String toAccount;
    private long amount;
    private LocalDateTime timestamp;
}
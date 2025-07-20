package com.kucp1127.SurakshaWalt.UserInformation.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfoModel {
    @Id
    private String username;
    private String adhaarNumber;
    private String panNumber;
    private String annualIncome;
    private String accountNumber;
    private String cardNumber;
    private String cibilScore;
    @ElementCollection
    @CollectionTable(
            name = "user_transactions",
            joinColumns = @JoinColumn(name = "username")
    )
    private List<TransactionInfo> transactions;
}

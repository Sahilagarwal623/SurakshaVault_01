package com.kucp1127.SurakshaWalt.NotificationHandeling.Entities;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    private String content;
    private LocalDateTime timeStamp = LocalDateTime.now();
}

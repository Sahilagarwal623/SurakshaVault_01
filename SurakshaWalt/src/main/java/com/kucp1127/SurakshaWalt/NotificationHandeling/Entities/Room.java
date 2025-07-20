package com.kucp1127.SurakshaWalt.NotificationHandeling.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Room {
    @ElementCollection
    private List<Message> messages = new ArrayList<>();
    @Id
    private String user1;
}

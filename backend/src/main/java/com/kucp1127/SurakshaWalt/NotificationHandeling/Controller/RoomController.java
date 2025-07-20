package com.kucp1127.SurakshaWalt.NotificationHandeling.Controller;

import com.kucp1127.SurakshaWalt.NotificationHandeling.Entities.Message;
import com.kucp1127.SurakshaWalt.NotificationHandeling.Entities.Room;
import com.kucp1127.SurakshaWalt.NotificationHandeling.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/rooms")
public class RoomController {


    @Autowired
    private RoomService roomService;


    @GetMapping("/message/{username}")
    public ResponseEntity<?> getAllMessages(@PathVariable String username){
        Optional<Room> room = roomService.findById(username);
        if (room.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        List<Message> messages = room.get().getMessages();
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/{username}")
    public ResponseEntity<?> sendNotification(@PathVariable String username){
        return ResponseEntity.ok(roomService.sendNotification(username));
    }

}

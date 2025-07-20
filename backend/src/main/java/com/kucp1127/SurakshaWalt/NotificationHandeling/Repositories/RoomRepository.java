package com.kucp1127.SurakshaWalt.NotificationHandeling.Repositories;


import com.kucp1127.SurakshaWalt.NotificationHandeling.Entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,String> {
}

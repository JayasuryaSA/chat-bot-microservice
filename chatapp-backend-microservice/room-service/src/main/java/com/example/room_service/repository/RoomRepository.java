package com.example.room_service.repository;

import com.example.room_service.model.Message;
import com.example.room_service.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends MongoRepository<Room,String> {
    Room findByRoomId(String roomId);

    List<Message> getMessagesByRoomId(String roomId);
}

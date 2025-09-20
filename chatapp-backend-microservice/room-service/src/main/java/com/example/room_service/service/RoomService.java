package com.example.room_service.service;



import com.example.room_service.model.Message;
import com.example.room_service.model.Room;
import com.example.room_service.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public ResponseEntity<?> createRoom(String roomId) {
        if (roomId == null || roomId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Room ID must not be empty");
        }

        if (roomRepository.findByRoomId(roomId) != null) {
            return ResponseEntity.badRequest().body("Room already exists");
        }

        Room room = new Room();
        room.setRoomId(roomId);
        room.setMessages(new ArrayList<>());

        roomRepository.save(room);

        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }



    public ResponseEntity<?> findByRoomId(String roomId) {
        Room room = roomRepository.findByRoomId(roomId);

        if(room ==null){
            return ResponseEntity.badRequest().body("Room not found!");
        }

        return ResponseEntity.ok(room);
    }

    public ResponseEntity<List<Message>> getMessagesResponse(String roomId) {
        Room room = roomRepository.findByRoomId(roomId);
        if (room == null || room.getMessages() == null || room.getMessages().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(room.getMessages());
    }


    public ResponseEntity<?> addMessageToRoom(String roomId, Message message) {
        Room room = roomRepository.findByRoomId(roomId);
        if (room == null) {
            return ResponseEntity.badRequest().body("Room not found");
        }

        if (room.getMessages() == null) {
            room.setMessages(new ArrayList<>());
        }

        if (message.getTimeStamp() == null) {
            message.setTimeStamp(LocalDateTime.now());
        }

        room.getMessages().add(message);
        roomRepository.save(room);

        return ResponseEntity.ok(message);
    }




}


package com.example.room_service.controller;

import com.example.room_service.dto.RoomRequest;
import com.example.room_service.model.Message;
import com.example.room_service.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/createRoom")
    public ResponseEntity<?> createRoom(@RequestBody RoomRequest request) {
        return roomService.createRoom(request.getRoomId());
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId) {
        return roomService.findByRoomId(roomId);
    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String roomId) {
        return roomService.getMessagesResponse(roomId);
    }

    @PostMapping("/{roomId}/messages")
    public ResponseEntity<?> addMessage(@PathVariable String roomId, @RequestBody Message message) {
        return roomService.addMessageToRoom(roomId, message);
    }
}

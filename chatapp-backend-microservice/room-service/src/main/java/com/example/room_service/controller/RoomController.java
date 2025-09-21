package com.example.room_service.controller;

import com.example.room_service.dto.RoomRequest;
import com.example.room_service.model.Message;
import com.example.room_service.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;

    @PostMapping("/createRoom")
    public ResponseEntity<?> createRoom(@RequestBody RoomRequest request) {
        logger.info("createRoom called with roomId: {}", request.getRoomId());
        return roomService.createRoom(request.getRoomId());
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId) {
        logger.info("joinRoom called with roomId: {}", roomId);
        return roomService.findByRoomId(roomId);
    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String roomId) {
        logger.info("getMessages called with roomId: {}", roomId);
        return roomService.getMessagesResponse(roomId);
    }

    @PostMapping("/{roomId}/messages")
    public ResponseEntity<?> addMessage(@PathVariable String roomId, @RequestBody Message message) {
        logger.info("addMessage called with roomId: {}", roomId);
        return roomService.addMessageToRoom(roomId, message);
    }

    // New handler for preflight CORS OPTIONS requests
    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        logger.info("Preflight OPTIONS request received");
        return ResponseEntity.ok().build();
    }
}

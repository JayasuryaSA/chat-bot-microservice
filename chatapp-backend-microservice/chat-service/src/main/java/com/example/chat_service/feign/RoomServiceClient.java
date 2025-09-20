package com.example.chat_service.feign;

import com.example.chat_service.model.Message;
import com.example.chat_service.model.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "room-service")
public interface RoomServiceClient {
    @GetMapping("/api/v1/rooms/{roomId}")
    ResponseEntity<Room> joinRoom(@PathVariable("roomId") String roomId);

    @PostMapping("/api/v1/rooms/{roomId}/messages")
    void addMessage(@PathVariable String roomId, @RequestBody Message message);
}


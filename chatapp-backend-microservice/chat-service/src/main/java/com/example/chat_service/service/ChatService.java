package com.example.chat_service.service;


import com.example.chat_service.feign.RoomServiceClient;
import com.example.chat_service.model.Message;
import com.example.chat_service.model.Room;
import com.example.chat_service.payload.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatService {
    @Autowired
    private RoomServiceClient roomServiceClient;

    public Message processAndSaveMessage(String roomId, MessageRequest request) {

        Room room = roomServiceClient.joinRoom(roomId).getBody();

        if (room == null) {
            throw new RuntimeException("Room not found");
        }

        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());

        roomServiceClient.addMessage(roomId, message);

        return message;
    }

}

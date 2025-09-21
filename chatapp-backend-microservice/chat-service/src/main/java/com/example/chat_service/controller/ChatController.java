package com.example.chat_service.controller;


import com.example.chat_service.model.Message;
import com.example.chat_service.payload.MessageRequest;
import com.example.chat_service.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
//@CrossOrigin("http://localhost:5173")
public class ChatController {

    @Autowired
   private ChatService chatService;

    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(@DestinationVariable String roomId, MessageRequest request) {
        return chatService.processAndSaveMessage(roomId, request);
    }

}

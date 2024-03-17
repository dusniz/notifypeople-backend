package com.example.messagingstompwebsocket.controller;

import com.example.messagingstompwebsocket.dto.ChatResponse;
import com.example.messagingstompwebsocket.dto.MessageResponse;
import com.example.messagingstompwebsocket.model.Chat;
import com.example.messagingstompwebsocket.model.Message;
import com.example.messagingstompwebsocket.service.ChatService;
import com.example.messagingstompwebsocket.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

@RestController
public class ChatController {

    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private MessageService messageService;
    @Autowired private ChatService chatService;

    @GetMapping("/chat/all/{userId}")
    public ChatResponse getChatsByUserId(@PathVariable Integer userId) {
        return ChatResponse.builder()
                .chats(chatService.findChatsByUserId(userId))
                .build();
    }

    @GetMapping("/chat")
    public ChatResponse getChatById(Integer chatId) {
        var chat = chatService.findChatById(chatId);
        return ChatResponse.builder()
                .chats(chat.stream().toList())
                .build();
    }

    @MessageMapping("/chats")
    public void createChat(@Payload Chat chat) {
        var savedChat = chatService.saveChat(chat);

        messagingTemplate.convertAndSendToUser(
                String.valueOf(chat.getFirstUserId()),
                "/queue/chats",
                savedChat
        );
        messagingTemplate.convertAndSendToUser(
                String.valueOf(chat.getSecondUserId()),
                "/queue/chats",
                savedChat
        );
    }

    @DeleteMapping("/chat")
    public void deleteChat(Integer chatId) {
        chatService.deleteChat(chatId);
    }

    @MessageMapping("/messages")
    public void sendMessage(@Payload Message message) throws Exception {
        var chat = chatService.findChatByUserIds(message.getSenderId(), message.getRecipientId());

        var savedMessage = messageService.saveMessage(
                message.getContent(),
                chat.orElseThrow(() -> new NoSuchElementException("users don't have chat")).getId(),
                message.getSenderId(),
                message.getRecipientId()
        );

        messagingTemplate.convertAndSendToUser(
                String.valueOf(savedMessage.getChatId()),
                "/queue/messages",
                savedMessage);
    }

    @GetMapping("/messages/{chatId}")
    public ResponseEntity<?> findChatMessages(@PathVariable Integer chatId) {
        return ResponseEntity
                .ok(messageService.findAllChatMessagesByChatId(chatId));
    }
}





package com.example.messagingstompwebsocket.service;

import com.example.messagingstompwebsocket.model.Chat;
import com.example.messagingstompwebsocket.model.Message;
import com.example.messagingstompwebsocket.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired private MessageRepository messageRepository;

    public List<Message> findAllChatMessagesByChatId(Integer chatId) {
        return messageRepository.findByChatId(chatId);
    }

    public Message saveMessage(String content, Integer chatId, Integer senderId, Integer recipientId) {
        Message message = Message.builder()
                .content(content)
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .date(LocalDate.now())
                .build();
        messageRepository.save(message);
        return message;
    }

    public Optional<Message> findMessageById(Integer id) {
        return messageRepository.findById(id);
    }
}

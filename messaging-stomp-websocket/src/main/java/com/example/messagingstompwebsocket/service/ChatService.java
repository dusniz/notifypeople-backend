package com.example.messagingstompwebsocket.service;

import com.example.messagingstompwebsocket.exception.SuchElementExistsException;
import com.example.messagingstompwebsocket.model.Chat;
import com.example.messagingstompwebsocket.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired private ChatRepository chatRepository;

    public Optional<Chat> findChatById(Integer id) {
        return chatRepository.findById(id);
    }

    public Optional<Chat> findChatByUserIds(Integer firstUserId, Integer secondUserId) {
        var chat = chatRepository.findByFirstUserIdAndSecondUserId(firstUserId, secondUserId);
        if (chat.isEmpty())
            chat = chatRepository.findByFirstUserIdAndSecondUserId(secondUserId, firstUserId);
        return chat;
    }

    public List<Chat> findChatsByUserId(Integer userId) {
        var userChats = chatRepository.findByFirstUserId(userId);
        userChats.addAll(chatRepository.findBySecondUserId(userId));
        return userChats;
    }

    public Chat saveChat(Chat chat) {
        if (chatRepository.findByFirstUserIdAndSecondUserId(chat.getFirstUserId(), chat.getSecondUserId()).isPresent() ||
            chatRepository.findByFirstUserIdAndSecondUserId(chat.getSecondUserId(), chat.getFirstUserId()).isPresent()) {
            throw new SuchElementExistsException("users already have chat");
        }
        chatRepository.save(chat);
        return chat;
    }

    public void deleteChat(Integer id) {
        chatRepository.deleteById(id);
    }
 }

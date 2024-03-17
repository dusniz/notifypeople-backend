package com.example.messagingstompwebsocket.dto;

import com.example.messagingstompwebsocket.model.Chat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatResponse {

    private List<Chat> chats;
}

package com.example.messagingstompwebsocket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MessageResponse {

    private Integer id;
    private Integer chatId;
    private Integer senderId;
    private Integer recipientId;
    private String content;

}

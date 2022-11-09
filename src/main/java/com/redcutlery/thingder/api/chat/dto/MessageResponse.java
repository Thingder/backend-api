package com.redcutlery.thingder.api.chat.dto;

import com.redcutlery.thingder.api.member.dto.find.MemberResponse;
import com.redcutlery.thingder.domain.chat.entity.ChatMessage;
import lombok.Data;

import java.util.UUID;

@Data
public class MessageResponse {
    private UUID uid;
    private MemberResponse sender;
    private String message;

    public MessageResponse(ChatMessage chatMessage) {
        this.uid = chatMessage.getUid();
        this.sender = new MemberResponse(chatMessage.getSender());
        this.message = chatMessage.getMessage();
    }
}

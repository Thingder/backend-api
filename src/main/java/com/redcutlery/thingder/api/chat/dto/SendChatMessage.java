package com.redcutlery.thingder.api.chat.dto;

import com.redcutlery.thingder.domain.chat.entity.ChatMessage;
import lombok.Data;

import java.util.UUID;

@Data
public class SendChatMessage {
    private UUID roomUid;
    private UUID userUid;
    private String message;

    public SendChatMessage(ChatMessage message) {
        this.roomUid = message.getRoom().getUid();
        this.userUid = message.getSender().getUid();
        this.message = message.getMessage();
    }
}

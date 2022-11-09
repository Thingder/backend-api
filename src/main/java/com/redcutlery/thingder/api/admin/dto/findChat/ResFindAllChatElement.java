package com.redcutlery.thingder.api.admin.dto.findChat;

import com.redcutlery.thingder.domain.chat.entity.ChatRoom;
import lombok.Data;

import java.util.UUID;

@Data
public class ResFindAllChatElement {
    private UUID uid;

    public ResFindAllChatElement(ChatRoom chatRoom) {
        this.uid = chatRoom.getUid();
    }
}

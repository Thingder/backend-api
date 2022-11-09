package com.redcutlery.thingder.api.admin.dto.findChat;

import com.redcutlery.thingder.domain.chat.entity.ChatRoom;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResFindAllChat {
    private List<ResFindAllChatElement> chats;

    public ResFindAllChat(List<ChatRoom> chatRooms) {
        this.chats = chatRooms.stream()
                .map(ResFindAllChatElement::new)
                .collect(Collectors.toList());
    }
}

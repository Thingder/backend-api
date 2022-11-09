package com.redcutlery.thingder.api.chat.dto;

import com.redcutlery.thingder.domain.chat.entity.ChatRoom;
import lombok.Data;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class FindRoomResponse {
    private UUID uid;
    private List<MessageResponse> messages;

    public FindRoomResponse(ChatRoom chatRoom) {
        this.uid = chatRoom.getUid();
        this.messages = chatRoom.getMessages().stream()
                .map(MessageResponse::new)
                .collect(Collectors.toList());
    }
}

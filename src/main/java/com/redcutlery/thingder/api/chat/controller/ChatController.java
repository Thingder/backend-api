package com.redcutlery.thingder.api.chat.controller;

import com.redcutlery.thingder.api.chat.dto.FindRoomResponse;
import com.redcutlery.thingder.api.chat.transaction.ChatTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatTransaction chatTransaction;

    @GetMapping("/{chatRoomUid}")
    public FindRoomResponse findRoom(@PathVariable UUID chatRoomUid) {
        return chatTransaction.findRoom(chatRoomUid);
    }
}

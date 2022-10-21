package com.redcutlery.thingder.api.chat.controller;

import com.redcutlery.thingder.api.chat.dto.MessageRequest;
import com.redcutlery.thingder.api.chat.transaction.ChatTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final ChatTransaction chatTransaction;

    @MessageMapping("/chat/message")
    public void message(MessageRequest message) {
        chatTransaction.message(message);
    }
}

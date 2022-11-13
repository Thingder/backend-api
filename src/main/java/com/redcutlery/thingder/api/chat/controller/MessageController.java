package com.redcutlery.thingder.api.chat.controller;

import com.redcutlery.thingder.api.chat.dto.MessageRequest;
import com.redcutlery.thingder.api.chat.transaction.ChatTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Log4j2
@Controller
@RequiredArgsConstructor
public class MessageController {
    private final ChatTransaction chatTransaction;

    @MessageMapping("/chat/message")
    public void message(MessageRequest message) {
        log.info(message);
//        chatTransaction.message(message);
    }
}

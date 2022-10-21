package com.redcutlery.thingder.api.chat.controller;

import com.redcutlery.thingder.api.chat.transaction.ChatTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatTransaction chatTransaction;
}

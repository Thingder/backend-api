package com.redcutlery.thingder.api.chat.controller;

import com.redcutlery.thingder.api.chat.dto.FindRoomResponse;
import com.redcutlery.thingder.api.chat.dto.report.ReqReportChat;
import com.redcutlery.thingder.api.chat.transaction.ChatTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{chatRoomUid}")
    public void report(@PathVariable UUID chatRoomUid, @RequestBody ReqReportChat reqReportChat) {
        chatTransaction.report(chatRoomUid, reqReportChat);
    }
}

package com.redcutlery.thingder.api.chat.transaction;

import com.redcutlery.thingder.api.chat.dto.MessageRequest;
import com.redcutlery.thingder.domain.chat.serivce.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class ChatTransaction {
    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatService chatService;

    public void message(MessageRequest messageRequest) {
        var message = chatService.saveMessage(messageRequest);
        messagingTemplate.convertAndSend(
                "/sub/chat/room" + messageRequest.getRoomUid()
                , message);
    }
}

package com.redcutlery.thingder.api.chat.transaction;

import com.redcutlery.thingder.api.chat.dto.FindRoomResponse;
import com.redcutlery.thingder.api.chat.dto.MessageRequest;
import com.redcutlery.thingder.api.chat.dto.SendChatMessage;
import com.redcutlery.thingder.api.chat.dto.report.ReqReportChat;
import com.redcutlery.thingder.domain.MemberRelation.entity.MemberRelation;
import com.redcutlery.thingder.domain.chat.entity.ChatRoom;
import com.redcutlery.thingder.domain.chat.serivce.ChatService;
import com.redcutlery.thingder.domain.member.MemberService;
import com.redcutlery.thingder.domain.report.chat.service.ChatReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class ChatTransaction {
    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatService chatService;
    private final ChatReportService chatReportService;
    private final MemberService memberService;

    public void message(MessageRequest messageRequest) {
        var message = chatService.saveMessage(messageRequest);
        messagingTemplate.convertAndSend(
                "/chat/room/" + messageRequest.getRoomUid()
                , new SendChatMessage(message));
    }

    public FindRoomResponse findRoom(UUID chatRoomUid) {
        return new FindRoomResponse(chatService.findByUid(chatRoomUid));
    }

    public void report(UUID chatRoomUid, ReqReportChat reqReportChat) {
        var chatroom = chatService.findByUid(chatRoomUid);
        var member = memberService.findByUid(reqReportChat.getSubjectUid());
        chatReportService.report(chatroom, member, reqReportChat.getMessage());
    }

    public ChatRoom chatAdmin() {
        var member = memberService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        var chat = new ChatRoom();
        chat.addMemberRelation(new MemberRelation(member, member, MemberRelation.RelationType.ADMIN));
        return chatService.save(chat);
    }
}

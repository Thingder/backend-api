package com.redcutlery.thingder.domain.chat.serivce;

import com.redcutlery.thingder.api.chat.dto.MessageRequest;
import com.redcutlery.thingder.domain.chat.entity.ChatMessage;
import com.redcutlery.thingder.domain.chat.entity.ChatRoom;
import com.redcutlery.thingder.domain.chat.repository.ChatMessageRepository;
import com.redcutlery.thingder.domain.chat.repository.ChatRoomRepository;
import com.redcutlery.thingder.domain.member.MemberService;
import com.redcutlery.thingder.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final MemberService memberService;

    public ChatMessage saveMessage(MessageRequest messageRequest) {
        var member = memberService.findByUid(messageRequest.getUserUid());

        var room = findByUid(messageRequest.getRoomUid());
        var chatMessage = new ChatMessage(member, room, messageRequest.getMessage());

        chatMessage.setChatRoom(room);

        return chatMessageRepository.save(chatMessage);
    }

    public ChatRoom findByUid(UUID roomUid) {
        return chatRoomRepository.findById(roomUid)
                .orElseThrow(() -> new BaseException.BadRequest("존재하지 않는 채팅방입니다."));
    }

    public List<ChatRoom> findAll() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom save(ChatRoom chat) {
        return chatRoomRepository.save(chat);
    }
}

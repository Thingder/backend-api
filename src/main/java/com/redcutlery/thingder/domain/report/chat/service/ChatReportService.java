package com.redcutlery.thingder.domain.report.chat.service;

import com.redcutlery.thingder.domain.chat.entity.ChatRoom;
import com.redcutlery.thingder.domain.member.MemberService;
import com.redcutlery.thingder.domain.member.entity.Member;
import com.redcutlery.thingder.domain.report.chat.entity.ChatReport;
import com.redcutlery.thingder.domain.report.chat.repository.ChatReportRepository;
import com.redcutlery.thingder.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class ChatReportService {
    private final ChatReportRepository chatReportRepository;
    private final MemberService memberService;

    public List<ChatReport> findAll() {
        return chatReportRepository.findAll();
    }

    public ChatReport findByUid(UUID chatReportUid) {
        return chatReportRepository
                .findById(chatReportUid)
                .orElseThrow(() -> new BaseException.NotFound("존재하지 않는 채팅 신고내역입니다."));
    }

    public ChatReport setStatus(UUID chatReportUid, ChatReport.Status status) {
        var chatReport = findByUid(chatReportUid);
        chatReport.setStatus(status);
        return chatReportRepository.save(chatReport);
    }

    public ChatReport report(ChatRoom chatroom, Member member, String message) {
        var chatReport = new ChatReport();
        chatReport.setChatRoom(chatroom);
        chatReport.setSubject(member);
        chatReport.setMessage(message);
        return chatReportRepository.save(chatReport);
    }
}

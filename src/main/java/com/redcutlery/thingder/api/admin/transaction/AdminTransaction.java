package com.redcutlery.thingder.api.admin.transaction;

import com.redcutlery.thingder.api.admin.dto.findAll.ResAdminFindAllMember;
import com.redcutlery.thingder.api.admin.dto.findAllChat.ResAdminFindAllChat;
import com.redcutlery.thingder.api.admin.dto.findAllProfile.ResAdminFindAllProfile;
import com.redcutlery.thingder.api.admin.dto.findChat.ResFindAllChat;
import com.redcutlery.thingder.api.admin.dto.review.ReqReview;
import com.redcutlery.thingder.api.admin.dto.reviewChat.ReqReviewChat;
import com.redcutlery.thingder.api.admin.dto.reviewProfile.ReqReviewProfile;
import com.redcutlery.thingder.domain.chat.serivce.ChatService;
import com.redcutlery.thingder.domain.member.MemberService;
import com.redcutlery.thingder.domain.member.entity.Member;
import com.redcutlery.thingder.domain.member.param.MemberRole;
import com.redcutlery.thingder.domain.report.chat.entity.ChatReport;
import com.redcutlery.thingder.domain.report.chat.service.ChatReportService;
import com.redcutlery.thingder.domain.report.profile.entity.ProfileReport;
import com.redcutlery.thingder.domain.report.profile.service.ProfileReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class AdminTransaction {
    private final MemberService memberService;
    private final ChatReportService chatReportService;
    private final ProfileReportService profileReportService;
    private final ChatService chatService;

    public ResAdminFindAllMember findAllMember() {
        var members = memberService.findAll();
        members = members.stream()
                .filter(member -> member.getStatus() == null)
                .filter(member -> member.getRoleSet().stream().noneMatch(role -> role.equals(MemberRole.ADMIN)))
                .collect(Collectors.toList());
        return new ResAdminFindAllMember(members);
    }

    public void reviewMember(UUID memberUid, ReqReview reqReview) {
        memberService.setStatus(memberUid, reqReview.getStatus());
    }

    public ResAdminFindAllChat findAllChatReport() {
        var chatReports = chatReportService.findAll();
        chatReports = chatReports.stream()
                .filter(chatReport -> chatReport.getStatus() == null)
                .collect(Collectors.toList());
        return new ResAdminFindAllChat(chatReports);
    }

    public ResAdminFindAllProfile findAllProfileReport() {
        var profileReports = profileReportService.findAll();
        profileReports = profileReports.stream()
                .filter(profileReport -> profileReport.getStatus() == null)
                .collect(Collectors.toList());
        return new ResAdminFindAllProfile(profileReports);
    }

    public void reviewChat(UUID chatReportUid, ReqReviewChat reqReviewChat) {
        var chatReport = chatReportService.setStatus(chatReportUid, reqReviewChat.getStatus());
        if (chatReport.getStatus().equals(ChatReport.Status.BAN))
            memberService.setStatus(chatReport.getSubject().getUid(), Member.Status.BANNED);
    }

    public void reviewProfile(UUID profileReportUid, ReqReviewProfile reqReviewProfile) {
        var profileReport = profileReportService.setStatus(profileReportUid, reqReviewProfile.getStatus());
        if (profileReport.getStatus().equals(ProfileReport.Status.BAN))
            memberService.setStatus(profileReport.getMember().getUid(), Member.Status.BANNED);
    }

    public ResFindAllChat findAllChat() {
        var chatRooms = chatService.findAll();
        chatRooms = chatRooms.stream().filter(chatRoom -> chatRoom.getMemberRelations().isEmpty())
                .collect(Collectors.toList());
        return new ResFindAllChat(chatRooms);
    }
}

package com.redcutlery.thingder.api.admin.controller;

import com.redcutlery.thingder.api.admin.dto.findAll.ResAdminFindAllMember;
import com.redcutlery.thingder.api.admin.dto.findAllChat.ResAdminFindAllChat;
import com.redcutlery.thingder.api.admin.dto.findAllProfile.ResAdminFindAllProfile;
import com.redcutlery.thingder.api.admin.dto.findChat.ResFindAllChat;
import com.redcutlery.thingder.api.admin.dto.review.ReqReview;
import com.redcutlery.thingder.api.admin.dto.reviewChat.ReqReviewChat;
import com.redcutlery.thingder.api.admin.dto.reviewProfile.ReqReviewProfile;
import com.redcutlery.thingder.api.admin.transaction.AdminTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminTransaction adminTransaction;

    @GetMapping("/members")
    public ResAdminFindAllMember findAllMember() {
        return adminTransaction.findAllMember();
    }

    @PostMapping("/{memberUid}")
    public void reviewMember(@PathVariable UUID memberUid, @RequestBody ReqReview reqReview) {
        adminTransaction.reviewMember(memberUid, reqReview);
    }

    @GetMapping("/report/chat")
    public ResAdminFindAllChat findAllChatReport() {
        return adminTransaction.findAllChatReport();
    }

    @GetMapping("/report/profile")
    public ResAdminFindAllProfile findAllProfileReport() {
        return adminTransaction.findAllProfileReport();
    }

    @PostMapping("/report/chat/{chatReportUid}")
    public void reviewChat(@PathVariable UUID chatReportUid, @RequestBody ReqReviewChat reqReviewChat) {
        adminTransaction.reviewChat(chatReportUid, reqReviewChat);
    }

    @PostMapping("/report/profile/{profileReportUid}")
    public void reviewProfile(@PathVariable UUID profileReportUid, @RequestBody ReqReviewProfile reqReviewProfile) {
        adminTransaction.reviewProfile(profileReportUid, reqReviewProfile);
    }

    @GetMapping("/chat")
    public ResFindAllChat findAllChat() {
        return adminTransaction.findAllChat();
    }
}

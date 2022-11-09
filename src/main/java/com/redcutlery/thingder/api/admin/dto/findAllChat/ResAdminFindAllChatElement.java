package com.redcutlery.thingder.api.admin.dto.findAllChat;

import com.redcutlery.thingder.domain.report.chat.entity.ChatReport;
import lombok.Data;

import java.util.UUID;

@Data
public class ResAdminFindAllChatElement {
    private UUID uid;
    private UUID subjectUid;
    private UUID chatRoomUid;
    private String message;

    public ResAdminFindAllChatElement(ChatReport chatReport) {
        this.uid = chatReport.getUid();
        this.subjectUid = chatReport.getSubject().getUid();
        this.chatRoomUid = chatReport.getChatRoom().getUid();
        this.message = chatReport.getMessage();
    }
}

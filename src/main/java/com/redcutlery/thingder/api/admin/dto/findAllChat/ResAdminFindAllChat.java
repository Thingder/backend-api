package com.redcutlery.thingder.api.admin.dto.findAllChat;

import com.redcutlery.thingder.domain.report.chat.entity.ChatReport;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResAdminFindAllChat {
    private List<ResAdminFindAllChatElement> chats;

    public ResAdminFindAllChat(List<ChatReport> chatReports) {
        this.chats = chatReports.stream()
                .map(ResAdminFindAllChatElement::new)
                .collect(Collectors.toList());
    }
}

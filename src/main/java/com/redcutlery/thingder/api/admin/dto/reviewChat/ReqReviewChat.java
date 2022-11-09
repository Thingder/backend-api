package com.redcutlery.thingder.api.admin.dto.reviewChat;

import com.redcutlery.thingder.domain.report.chat.entity.ChatReport;
import lombok.Data;

@Data
public class ReqReviewChat {
    private ChatReport.Status status;
}

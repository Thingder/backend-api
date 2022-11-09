package com.redcutlery.thingder.api.chat.dto.report;

import lombok.Data;

import java.util.UUID;

@Data
public class ReqReportChat {
    private UUID subjectUid;
    private String message;
}

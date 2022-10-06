package com.redcutlery.thingder.module.naver.dto.SMSRequest;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class SMSRequest {
    @NonNull
    private String type;
    private String contentType;
    private String countryCode;
    @NonNull
    private String from;
    private String subject;
    @NonNull
    private String content;
    @Valid
    private List<MessagesItem> messages;
    private List<FilesItem> files;
    private String reserveTime;
    private String reserveTimeZone;
    private String scheduleCode;
}
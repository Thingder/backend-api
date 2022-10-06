package com.redcutlery.thingder.module.naver.dto.SMSRequest;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class MessagesItem {
    @NonNull
    private String to;
    private String subject;
    private String content;
}
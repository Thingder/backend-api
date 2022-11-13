package com.redcutlery.thingder.api.chat.dto;

import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
@ToString
public class MessageRequest {
    private String roomUid;
    private String message;
}

package com.redcutlery.thingder.api.chat.dto;

import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.util.UUID;

@Data
@Log4j2
@ToString
public class MessageRequest {
    private UUID roomUid;
    private String message;
}

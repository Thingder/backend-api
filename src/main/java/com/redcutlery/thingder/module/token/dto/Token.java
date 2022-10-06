package com.redcutlery.thingder.module.token.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Token {
    private String token;
    private String subject;
    private Long expiration;
}

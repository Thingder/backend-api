package com.redcutlery.thingder.module.token.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyResult {
    private boolean success;
    private String subject;
}

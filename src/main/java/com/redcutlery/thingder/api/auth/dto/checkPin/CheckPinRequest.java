package com.redcutlery.thingder.api.auth.dto.checkPin;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CheckPinRequest {
    private String phone;
    private String pin;
}

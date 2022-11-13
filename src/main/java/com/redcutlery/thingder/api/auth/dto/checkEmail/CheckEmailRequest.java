package com.redcutlery.thingder.api.auth.dto.checkEmail;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CheckEmailRequest {
    private String email;
    private String pin;
}

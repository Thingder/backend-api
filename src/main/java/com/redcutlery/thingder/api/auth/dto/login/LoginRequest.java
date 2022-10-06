package com.redcutlery.thingder.api.auth.dto.login;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginRequest {
    private String email;
    private String password;
}

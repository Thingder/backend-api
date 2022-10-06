package com.redcutlery.thingder.api.auth.dto.login;

import com.redcutlery.thingder.module.token.dto.Token;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginResponse {
    private String email;
    private String token;
    private Long expiration;

    public LoginResponse(Token token) {
        this.email = token.getSubject();
        this.token = token.getToken();
        this.expiration = token.getExpiration();
    }
}


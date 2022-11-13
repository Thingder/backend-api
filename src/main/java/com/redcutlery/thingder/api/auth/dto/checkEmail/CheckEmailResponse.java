package com.redcutlery.thingder.api.auth.dto.checkEmail;

import com.redcutlery.thingder.module.token.dto.Token;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CheckEmailResponse {
    private String email;
    private String token;
    private Long expiration;

    public CheckEmailResponse(Token token) {
        this.email = token.getSubject();
        this.token = token.getToken();
        this.expiration = token.getExpiration();
    }
}

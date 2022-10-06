package com.redcutlery.thingder.api.auth.dto.checkPin;

import com.redcutlery.thingder.module.token.dto.Token;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CheckPinResponse {
    private String phone;
    private String token;
    private Long expiration;

    public CheckPinResponse(Token token) {
        this.phone = token.getSubject();
        this.token = token.getToken();
        this.expiration = token.getExpiration();
    }
}

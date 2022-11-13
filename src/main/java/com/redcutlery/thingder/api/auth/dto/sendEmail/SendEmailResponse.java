package com.redcutlery.thingder.api.auth.dto.sendEmail;

import com.redcutlery.thingder.domain.emailPin.entity.EmailPin;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class SendEmailResponse {
    private String email;
    private LocalDateTime expireAt;

    public SendEmailResponse(EmailPin sendEmail) {
        this.email = sendEmail.getEmail();
        this.expireAt = sendEmail.getExpireAt();
    }
}

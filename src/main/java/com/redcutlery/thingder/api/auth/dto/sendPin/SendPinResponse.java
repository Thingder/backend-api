package com.redcutlery.thingder.api.auth.dto.sendPin;

import com.redcutlery.thingder.domain.pin.entity.Pin;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class SendPinResponse {
    private String phone;
    private LocalDateTime expireAt;

    public SendPinResponse(Pin pin) {
        this.phone = pin.getPhone();
        this.expireAt = pin.getExpireAt();
    }
}

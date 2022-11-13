package com.redcutlery.thingder.domain.emailPin.service;

import com.redcutlery.thingder.api.auth.dto.checkEmail.CheckEmailRequest;
import com.redcutlery.thingder.domain.emailPin.entity.EmailPin;
import com.redcutlery.thingder.domain.emailPin.repository.EmailPinRepository;
import com.redcutlery.thingder.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmailPinService {
    private final EmailPinRepository emailPinRepository;
    @Value(value = "${pin.length}")
    private Integer PIN_LENGTH;
    @Value(value = "${pin.expiration_time}")
    private Integer PIN_EXPIRATION_TIME;

    public EmailPin findOrCreate(String email) {
        var exist = emailPinRepository.findByEmail(email);
        if (exist.isPresent()) {
            var emailPin = exist.get();
            emailPin.setExpireAt(LocalDateTime.now().plusSeconds(PIN_EXPIRATION_TIME));
            return emailPinRepository.save(emailPin);
        }
        var emailPin = new EmailPin(email, generate(PIN_LENGTH), LocalDateTime.now().plusSeconds(PIN_EXPIRATION_TIME));
        return emailPinRepository.save(emailPin);
    }

    public void check(CheckEmailRequest checkEmailRequest) {
        var exist = emailPinRepository.findByEmail(checkEmailRequest.getEmail());
        if (exist.isEmpty())
            throw new BaseException.BadRequest("유효하지 않은 pin 입니다.(1)");

        var pin = exist.get();
        if (!pin.getNumber().equals(checkEmailRequest.getPin()))
            throw new BaseException.BadRequest("유효하지 않은 pin 입니다.(2)");

        if (!pin.getExpireAt().isAfter(LocalDateTime.now()))
            throw new BaseException.BadRequest("유효하지 않은 pin 입니다.(3)");

        emailPinRepository.delete(pin);
    }

    public String generate(Integer size) {
        Random random = new Random();
        StringBuilder buffer = new StringBuilder();
        int num = 0;

        while (buffer.length() < size) {
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();
    }
}

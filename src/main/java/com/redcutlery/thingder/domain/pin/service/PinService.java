package com.redcutlery.thingder.domain.pin.service;

import com.redcutlery.thingder.api.auth.dto.checkPin.CheckPinRequest;
import com.redcutlery.thingder.domain.pin.entity.Pin;
import com.redcutlery.thingder.domain.pin.repository.PinRepository;
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
public class PinService {
    private final PinRepository pinRepository;
    @Value(value = "${pin.length}")
    private Integer PIN_LENGTH;
    @Value(value = "${pin.expiration_time}")
    private Integer PIN_EXPIRATION_TIME;

    public Pin findOrCreate(String phone) {
        var exist = pinRepository.findByPhone(phone);
        if (exist.isPresent())
            return exist.get();
        var pin = new Pin(phone, generate(PIN_LENGTH), LocalDateTime.now().plusSeconds(PIN_EXPIRATION_TIME));
        return pinRepository.save(pin);
    }

    public void check(CheckPinRequest checkPinRequest) {
        var exist = pinRepository.findByPhone(checkPinRequest.getPhone());
        if (exist.isEmpty())
            throw new BaseException.BadRequest("유효하지 않은 pin 입니다.(1)");

        var pin = exist.get();
        if (!pin.getNumber().equals(checkPinRequest.getPin()))
            throw new BaseException.BadRequest("유효하지 않은 pin 입니다.(2)");

        if (!pin.getExpireAt().isAfter(LocalDateTime.now()))
            throw new BaseException.BadRequest("유효하지 않은 pin 입니다.(3)");

        pinRepository.delete(pin);
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

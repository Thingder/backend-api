package com.redcutlery.thingder.module.email.service;

import com.redcutlery.thingder.domain.emailPin.entity.EmailPin;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSenderImpl mailSender;
    @Value("${spring.mail.sender-address}")
    private String SENDER_ADDRESS;

    public void sendPin(EmailPin emailPin) {
        var message = new SimpleMailMessage();
        message.setTo(emailPin.getEmail());
        message.setFrom(SENDER_ADDRESS);
        message.setSubject("Thingder 인증번호");
        message.setText("Thingder 인증번호 [" + emailPin.getNumber() + "]");
        mailSender.send(message);
    }
}

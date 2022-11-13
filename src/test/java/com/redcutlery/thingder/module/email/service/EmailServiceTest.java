package com.redcutlery.thingder.module.email.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailServiceTest {
    @Autowired
    EmailService emailService;

    @Test
    void sendPinTest() {
//        emailService.sendPin("otm0937@naver.com");
    }
}
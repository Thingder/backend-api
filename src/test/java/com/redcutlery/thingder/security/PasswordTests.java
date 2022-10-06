package com.redcutlery.thingder.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTests {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncode() {
        var password = "1111";
        var enPw = passwordEncoder.encode(password);
        System.out.println("enPw: " + enPw);
        var matchResult = passwordEncoder.matches(password, enPw);
        System.out.println("matchResult: " + matchResult);
    }
}

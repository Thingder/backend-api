package com.redcutlery.thingder.api.auth.controller;

import com.redcutlery.thingder.api.auth.dto.checkEmail.CheckEmailRequest;
import com.redcutlery.thingder.api.auth.dto.checkEmail.CheckEmailResponse;
import com.redcutlery.thingder.api.auth.dto.checkPin.CheckPinRequest;
import com.redcutlery.thingder.api.auth.dto.checkPin.CheckPinResponse;
import com.redcutlery.thingder.api.auth.dto.login.LoginRequest;
import com.redcutlery.thingder.api.auth.dto.login.LoginResponse;
import com.redcutlery.thingder.api.auth.dto.register.RegisterRequest;
import com.redcutlery.thingder.api.auth.dto.register.RegisterResponse;
import com.redcutlery.thingder.api.auth.dto.resetPassword.ResetPasswordRequest;
import com.redcutlery.thingder.api.auth.dto.sendEmail.SendEmailResponse;
import com.redcutlery.thingder.api.auth.dto.sendPin.SendPinResponse;
import com.redcutlery.thingder.api.auth.transaction.AuthTransaction;
import com.redcutlery.thingder.domain.member.MemberService;
import com.redcutlery.thingder.domain.member.param.MemberRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@Log4j2
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthTransaction authTransaction;

    @PostMapping("/pin/send")
    public SendPinResponse sendPin(@RequestParam String phone) {
        return authTransaction.sendPin(phone);
    }

    @PostMapping("/pin/check")
    public CheckPinResponse checkPin(@RequestBody CheckPinRequest checkPinRequest) {
        return authTransaction.checkPin(checkPinRequest);
    }

    @PostMapping("/email/send")
    public SendEmailResponse sendEmail(@RequestParam String email) {
        return authTransaction.sendEmail(email);
    }

    @PostMapping("/email/check")
    public CheckEmailResponse checkEmail(@RequestParam CheckEmailRequest checkEmailRequest) {
        return authTransaction.checkEmail(checkEmailRequest);
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody @Valid RegisterRequest registerRequest) {
        return authTransaction.register(registerRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return authTransaction.login(loginRequest);
    }

    @PostMapping("/reset-password")
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        authTransaction.resetPassword(resetPasswordRequest);
    }

    @PostMapping("/create-admin")
    public void createAdmin(@RequestBody @Valid LoginRequest loginRequest) {
        authTransaction.createAdmin(loginRequest);
    }

    private final MemberService memberService;

    @GetMapping("/my")
    @Transactional
    public Set<MemberRole> my() {
        var context = SecurityContextHolder.getContext();

        var authentication = context.getAuthentication();

        var authorities = authentication.getAuthorities();
        var credentials = authentication.getCredentials();
        var details = authentication.getDetails();
        var name = authentication.getName();
        var principal = authentication.getPrincipal();


        log.info("context: " + context);
        log.info("authentication: " + authentication);
        log.info("authorities: " + authorities);
        log.info("credentials: " + credentials);
        log.info("details: " + details);
        log.info("name: " + name);
        log.info("principal: " + principal);
        var member = memberService.findByEmail(name);
        log.info(member);
        return member.getRoleSet();
    }
}

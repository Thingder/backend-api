package com.redcutlery.thingder.domain.auth.serivce;

import com.redcutlery.thingder.api.auth.dto.checkEmail.CheckEmailRequest;
import com.redcutlery.thingder.api.auth.dto.checkPin.CheckPinRequest;
import com.redcutlery.thingder.api.auth.dto.login.LoginRequest;
import com.redcutlery.thingder.api.auth.dto.register.RegisterRequest;
import com.redcutlery.thingder.api.auth.dto.resetPassword.ResetPasswordRequest;
import com.redcutlery.thingder.domain.emailPin.entity.EmailPin;
import com.redcutlery.thingder.domain.emailPin.service.EmailPinService;
import com.redcutlery.thingder.domain.member.MemberService;
import com.redcutlery.thingder.domain.member.entity.Member;
import com.redcutlery.thingder.domain.pin.entity.Pin;
import com.redcutlery.thingder.domain.pin.service.PinService;
import com.redcutlery.thingder.domain.security.service.UserDetailsServiceImpl;
import com.redcutlery.thingder.exception.BaseException;
import com.redcutlery.thingder.module.email.service.EmailService;
import com.redcutlery.thingder.module.naver.dto.SMSRequest.MessagesItem;
import com.redcutlery.thingder.module.naver.service.NaverService;
import com.redcutlery.thingder.module.token.dto.Token;
import com.redcutlery.thingder.module.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService {
    private final PinService pinService;
    private final EmailPinService emailPinService;
    private final EmailService emailService;
    private final NaverService naverService;
    private final TokenService tokenService;
    private final MemberService memberService;
    private final UserDetailsServiceImpl userDetailsService;

    public Pin sendPin(String phone) {
        var pin = pinService.findOrCreate(phone);

        var messagesItem = new MessagesItem(phone);
        messagesItem.setContent("Thingder 인증번호 [" + pin.getNumber() + "]");
        naverService.sendSms(List.of(messagesItem));

        return pin;
    }

    public Token checkPin(CheckPinRequest checkPinRequest) {
        pinService.check(checkPinRequest);
        return tokenService.generatePin(checkPinRequest.getPhone());
    }

    public EmailPin sendEmail(String email) {
        var emailPin = emailPinService.findOrCreate(email);

        emailService.sendPin(emailPin);
        return emailPin;
    }

    public Token checkEmail(CheckEmailRequest checkEmailRequest) {
        emailPinService.check(checkEmailRequest);
        return tokenService.generateEmail(checkEmailRequest.getEmail());
    }

    public Token register(RegisterRequest registerRequest) {
        var verifyResult = tokenService.validatePin(registerRequest.getPinToken());

        if (!verifyResult.isSuccess())
            throw new BaseException.BadRequest("유효하지 않은 pin 토큰입니다.");

        if (memberService.existByEmail(registerRequest.getEmail()))
            throw new BaseException.BadRequest("동일한 이메일의 멤버가 존재합니다.");

        var member = memberService.register(registerRequest, verifyResult.getSubject());

        var userDetails = userDetailsService.loadUserByUsername(member.getEmail());
        var authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenService.generateAccess(member.getEmail());
    }

    public Member resetPassword(ResetPasswordRequest resetPasswordRequest) {
        var verifyResult = tokenService.validatePin(resetPasswordRequest.getEmailToken());

        if (!verifyResult.isSuccess())
            throw new BaseException.BadRequest("유효하지 않은 이메일 토큰입니다.");

        var member = memberService.findByEmail(verifyResult.getSubject());

        return memberService.setPassword(member, resetPasswordRequest.getPassword());
    }

    public Token login(LoginRequest loginRequest) {
        var member = memberService.findByEmail(loginRequest.getEmail());

        if (!loginRequest.getPassword().equals(member.getPassword()))
            throw new BaseException.BadRequest("잘못된 계정 정보입니다.");
        var userDetails = userDetailsService.loadUserByUsername(member.getEmail());
        var authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenService.generateAccess(member.getEmail());
    }

    public void createAdmin(LoginRequest loginRequest) {
        memberService.createAdmin(loginRequest);
    }
}

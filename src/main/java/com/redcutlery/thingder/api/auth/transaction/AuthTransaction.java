package com.redcutlery.thingder.api.auth.transaction;

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
import com.redcutlery.thingder.domain.auth.serivce.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class AuthTransaction {
    private final AuthService authService;

    public SendPinResponse sendPin(String phone) {
        return new SendPinResponse(authService.sendPin(phone));
    }

    public CheckPinResponse checkPin(CheckPinRequest checkPinRequest) {
        return new CheckPinResponse(authService.checkPin(checkPinRequest));
    }

    public SendEmailResponse sendEmail(String email) {
        return new SendEmailResponse(authService.sendEmail(email));
    }

    public CheckEmailResponse checkEmail(CheckEmailRequest checkEmailRequest) {
        return new CheckEmailResponse(authService.checkEmail(checkEmailRequest));
    }

    public RegisterResponse register(RegisterRequest registerRequest) {
        return new RegisterResponse(authService.register(registerRequest));
    }

    public LoginResponse login(LoginRequest loginRequest) {
        return new LoginResponse(authService.login(loginRequest));
    }

    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        authService.resetPassword(resetPasswordRequest);
    }

    public void createAdmin(LoginRequest loginRequest) {
        authService.createAdmin(loginRequest);
    }
}

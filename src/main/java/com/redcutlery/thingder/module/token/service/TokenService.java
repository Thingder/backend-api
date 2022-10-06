package com.redcutlery.thingder.module.token.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.redcutlery.thingder.domain.security.service.UserDetailsServiceImpl;
import com.redcutlery.thingder.exception.BaseException;
import com.redcutlery.thingder.module.token.dto.Token;
import com.redcutlery.thingder.module.token.dto.VerifyResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Log4j2
@Service
@RequiredArgsConstructor
public class TokenService {
    @Value(value = "${token.pin.ttl}")
    private Long PIN_TTL;
    @Value(value = "${token.access.ttl}")
    private Long ACCESS_TTL;
    @Value(value = "${token.refresh.ttl}")
    private Long REFRESH_TTL;
    @Value(value = "${token.pin.secret}")
    private String PIN_SECRET;
    @Value(value = "${token.access.secret}")
    private String ACCESS_SECRET;
    @Value(value = "${token.refresh.secret}")
    private String REFRESH_SECRET;

    private final UserDetailsServiceImpl userDetailsService;

    public Token generatePin(String phone) {
        return generate(phone, PIN_TTL, PIN_SECRET);
    }

    public Token generateAccess(String email) {
        return generate(email, ACCESS_TTL, ACCESS_SECRET);
    }

    public VerifyResult validatePin(String token) {
        return validate(token, PIN_SECRET);
    }

    public VerifyResult validateAccess(String token) {
        return validate(token, ACCESS_SECRET);
    }

    public Authentication getAuthentication(String token) {
        var validate = validate(token, ACCESS_SECRET);
        if (!validate.isSuccess())
            throw new BaseException.Unauthorized("비정상적인 토큰입니다.");
        var userDetails = userDetailsService.loadUserByUsername(validate.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private Token generate(String subject, Long ttl, String key) {
        var token = JWT.create()
                .withSubject(subject)
                .withExpiresAt(Instant.now().plusSeconds(ttl))
                .sign(Algorithm.HMAC256(key));
        return new Token(token, subject, ttl);
    }

    private VerifyResult validate(String token, String key) {
        try {
            DecodedJWT verify = JWT.require(Algorithm.HMAC256(key)).build().verify(token);
            return new VerifyResult(true, verify.getSubject());
        } catch (Exception ex) {
            DecodedJWT decode = null;
            try {
                decode = JWT.decode(token);
            } catch (Exception e) {
                throw new BaseException.BadRequest("유효하지 않은 토큰 형식입니다.");
            }
            return new VerifyResult(false, decode.getSubject());
        }
    }
}

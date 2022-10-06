package com.redcutlery.thingder.config.security.filter;

import com.redcutlery.thingder.module.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class ApiCheckFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        log.info("REQUESTURI: " + request.getRequestURI());
        log.info("ApiCheckFilter..............................");
        log.info("ApiCheckFilter..............................");
        log.info("ApiCheckFilter..............................");

        String token = request.getHeader("Authorization");
        log.info("token: " + token);
        if (StringUtils.hasText(token)) {
            SecurityContextHolder.getContext().setAuthentication(
                    tokenService.getAuthentication(token));
        }
        log.info("authetication : " + SecurityContextHolder.getContext().getAuthentication());
        filterChain.doFilter(request, response);
    }
}

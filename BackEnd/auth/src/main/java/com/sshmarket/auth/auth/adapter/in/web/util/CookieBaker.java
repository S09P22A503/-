package com.sshmarket.auth.auth.adapter.in.web.util;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookieBaker {

    private final Environment environment;
    private static Integer EXPIRATION;

    @PostConstruct
    private void init() {
        EXPIRATION = Integer.parseInt(environment.getProperty("jwt.expiration"));
    }

    public Cookie bakeJwtCookie(String token) {
        Cookie cookie = new Cookie("jwt",token);
        cookie.setMaxAge(EXPIRATION);
        cookie.setPath("/");
        return cookie;
    }

}

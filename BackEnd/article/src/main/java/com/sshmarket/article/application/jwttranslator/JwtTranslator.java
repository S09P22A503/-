package com.sshmarket.article.application.jwttranslator;

import com.sshmarket.article.dto.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class JwtTranslator {

    private static String SECRET;

    private static Long EXPIRATION;

    private final Environment environment;

    @PostConstruct
    private void init() {
        SECRET = environment.getProperty("jwt.secret");
        EXPIRATION = Long.parseLong(environment.getProperty("jwt.expiration"));
    }

    public Member translate(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            return Member.createWithPublicInfo(claims.get("id", Long.class), claims.get("nickname", String.class), claims.get("profile", String.class));
        } catch (Exception e) {
            return null;
        }
    }

}
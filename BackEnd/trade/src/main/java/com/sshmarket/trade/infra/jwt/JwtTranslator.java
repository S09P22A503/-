package com.sshmarket.trade.infra.jwt;

import com.sshmarket.trade.domain.Member;
import com.sshmarket.trade.exception.ExpiredTokenException;
import com.sshmarket.trade.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
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
            return Member.createWithPublicInfo(claims.get("id", Long.class),
                    claims.get("nickname", String.class), claims.get("profile", String.class));
        } catch (Exception e) {
            return null;
        }
    }

    public Long getUserId(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            return claims.get("id", Long.class);
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException("만료된 토큰입니다.");
        } catch (Exception e) {
            throw new InvalidTokenException(e.getMessage());
        }
    }

}

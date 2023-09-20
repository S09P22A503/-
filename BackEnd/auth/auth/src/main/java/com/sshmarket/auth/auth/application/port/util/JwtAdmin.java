package com.sshmarket.auth.auth.application.port.util;

import com.sshmarket.auth.auth.domain.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAdmin {

    private static String SECRET;

    private static Long EXPIRATION;

    private final Environment environment;

    @PostConstruct
    public void init() {
        SECRET = environment.getProperty("jwt.secret");
        EXPIRATION = Long.parseLong(environment.getProperty("jwt.expiration"));
    }

    public String generateToken(Member member) {
        return Jwts.builder()
                .setSubject(member.getId().toString())
                .claim("id",member.getId())
                .claim("nickname",member.getNickname())
                .claim("profile",member.getProfile())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJwt(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

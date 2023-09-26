package com.sshmarket.auth.auth.adapter.in.web;

import com.sshmarket.auth.auth.adapter.in.web.request.dto.RequestLoginDto;
import com.sshmarket.auth.auth.adapter.in.web.request.dto.RequestRegisterDto;
import com.sshmarket.auth.auth.adapter.in.web.request.valid.AllowedContentType;
import com.sshmarket.auth.auth.adapter.in.web.response.HttpResponse;
import com.sshmarket.auth.auth.adapter.in.web.util.CookieBaker;
import com.sshmarket.auth.auth.application.port.in.LoginUseCase;
import com.sshmarket.auth.auth.application.port.in.SignupUseCase;
import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Component
@RestController
@RequiredArgsConstructor
public class OauthController {

    private final LoginUseCase loginUseCase;

    private final SignupUseCase signupUseCase;

    private final CookieBaker cookieBaker;


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid RequestRegisterDto requestRegisterDto, HttpServletResponse httpServletResponse) {
        String token = signupUseCase.signup(
                requestRegisterDto.getCode(),
                requestRegisterDto.getNickname(),
                requestRegisterDto.getProfile()
                );
        httpServletResponse.addCookie(cookieBaker.bakeJwtCookie(token));
        return HttpResponse.ok(HttpStatus.OK, "회원 가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid RequestLoginDto requestLoginDto, HttpServletResponse httpServletResponse) throws IOException {
        String token = loginUseCase.login(requestLoginDto.getCode());
        if (token == null) {
            return HttpResponse.fail(HttpStatus.SEE_OTHER, "존재하지 않는 회원입니다. 회원가입이 필요합니다.");
        }
        httpServletResponse.addCookie(cookieBaker.bakeJwtCookie(token));
        return HttpResponse.ok(HttpStatus.OK, "로그인이 성공했습니다.");
    }



}

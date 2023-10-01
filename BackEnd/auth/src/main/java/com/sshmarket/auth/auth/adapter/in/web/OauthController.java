package com.sshmarket.auth.auth.adapter.in.web;

import com.sshmarket.auth.auth.adapter.in.web.dto.RequestLoginDto;
import com.sshmarket.auth.auth.adapter.in.web.dto.RequestRegisterDto;
import com.sshmarket.auth.auth.adapter.in.web.dto.ResponseDto;
import com.sshmarket.auth.auth.adapter.in.web.response.HttpResponse;
import com.sshmarket.auth.auth.adapter.in.web.util.CookieBaker;
import com.sshmarket.auth.auth.application.port.in.LoginUseCase;
import com.sshmarket.auth.auth.application.port.in.SignupUseCase;
import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin("*")
public class OauthController {

    private final LoginUseCase loginUseCase;

    private final SignupUseCase signupUseCase;

    private final CookieBaker cookieBaker;


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid RequestRegisterDto requestRegisterDto, HttpServletResponse httpServletResponse) {
        ResponseDto responseDto = ResponseDto.getMemberInfo(signupUseCase.signup(
                requestRegisterDto.getCode(),
                requestRegisterDto.getNickname(),
                requestRegisterDto.getProfile()
                ));
        Cookie cookie = cookieBaker.bakeJwtCookie(responseDto.popToken());
        httpServletResponse.addCookie(cookie);
        responseDto.pushToken(String.valueOf(cookie.getMaxAge()));
        return HttpResponse.okWithData(HttpStatus.OK, "회원 가입이 완료되었습니다.", responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid RequestLoginDto requestLoginDto, HttpServletResponse httpServletResponse) throws IOException {
        ResponseDto responseDto = ResponseDto.getMemberInfo(
        loginUseCase.login(requestLoginDto.getCode()));
        if (responseDto.getId() == 0) {
            return HttpResponse.fail(HttpStatus.SEE_OTHER, responseDto.popToken());
        }
        Cookie cookie = cookieBaker.bakeJwtCookie(responseDto.popToken());
        httpServletResponse.addCookie(cookie);
        responseDto.pushToken(String.valueOf(cookie.getMaxAge()));
        return HttpResponse.okWithData(HttpStatus.OK, "로그인이 성공했습니다.", responseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse httpServletResponse) {
        Cookie cookie = cookieBaker.burnJwtCookie();
        httpServletResponse.addCookie(cookie);
        return HttpResponse.ok(HttpStatus.OK, "로그아웃이 완료되었습니다.");
    }



}

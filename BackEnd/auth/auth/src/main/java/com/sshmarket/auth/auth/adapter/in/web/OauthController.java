package com.sshmarket.auth.auth.adapter.in.web;

import com.sshmarket.auth.auth.adapter.in.web.request.valid.AllowedContentType;
import com.sshmarket.auth.auth.adapter.in.web.response.HttpResponse;
import com.sshmarket.auth.auth.application.port.in.LoginUseCase;
import com.sshmarket.auth.auth.application.port.in.SignupUseCase;
import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @NotBlank(message = "계정 정보를 불러올 수 없습니다.") @RequestParam
            String code,

            @NotBlank(message = "닉네임을 입력해주세요.")
            @Size(min = 4, max = 20, message = "닉네임은 4자 이상 20자 이하로 입력해주세요.")
            @RequestParam
            String nickname,

            @AllowedContentType(allowedTypes = {"image/jpg", "image/jpeg", "image/png"},
                    allowedExtensions = {"jpg", "jpeg", "png"},
                    message = "jpg,jpeg,png 파일만 등록 가능합니다.")
            @RequestPart(value = "profile", required = false)
            MultipartFile profile,
            HttpServletResponse httpServletResponse
    ) {
        String token = signupUseCase.signup(code, nickname, profile);
        httpServletResponse.addCookie(bakeJwtCookie(token));
        return HttpResponse.ok(HttpStatus.OK, "회원 가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @NotBlank(message = "계정 정보를 불러올 수 없습니다.")
            String code,
            HttpServletResponse httpServletResponse
            )
            throws IOException {
        String token = loginUseCase.login(code);
        if (token == null) {
            return HttpResponse.fail(HttpStatus.SEE_OTHER, "존재하지 않는 회원입니다. 회원가입이 필요합니다.");
        }
        httpServletResponse.addCookie(bakeJwtCookie(token));
        return HttpResponse.ok(HttpStatus.OK, "로그인이 성공했습니다.");
    }

    private Cookie bakeJwtCookie(String token) {
        Cookie cookie = new Cookie("jwt",token);
        cookie.setMaxAge(864000);
        return cookie;
    }


}

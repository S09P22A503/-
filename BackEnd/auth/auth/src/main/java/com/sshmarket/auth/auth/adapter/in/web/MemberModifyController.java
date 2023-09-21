package com.sshmarket.auth.auth.adapter.in.web;

import com.sshmarket.auth.auth.adapter.in.web.request.valid.AllowedContentType;
import com.sshmarket.auth.auth.adapter.in.web.response.HttpResponse;
import com.sshmarket.auth.auth.application.port.in.ModifyMemberUseCase;
import com.sshmarket.auth.auth.domain.Member;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Component
@RestController
@RequiredArgsConstructor
public class MemberModifyController {

    private final ModifyMemberUseCase modifyMemberUseCase;

    @GetMapping("/members/check/{nickname}")
    public ResponseEntity<?> memberNicknameExistCheck(
            @NotBlank(message = "닉네임을 입력해주세요.")
            @Size(min = 4, max = 20, message = "닉네임은 4자 이상 20자 이하로 입력해주세요.")
            @PathVariable
            String nickname
    ) {
        if (modifyMemberUseCase.existCheckMemberNickname(nickname)) {
            return HttpResponse.ok(HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다.");
        } else {
            return HttpResponse.ok(HttpStatus.OK, "사용 가능한 닉네임입니다.");
        }
    }

    @PatchMapping("/members/nickname")
    public ResponseEntity<?> memberNicknameModify(
            @NotBlank(message = "닉네임을 입력해주세요.")
            @Size(min = 4, max = 20, message = "닉네임은 4자 이상 20자 이하로 입력해주세요.")
            String nickname,
            @CookieValue(value = "jwt", required = true)
            String token,
            HttpServletResponse httpServletResponse
    ) {
        String newToken = modifyMemberUseCase.modifyMemberNickname(token, nickname);
        httpServletResponse.addCookie(bakeJwtCookie(newToken));
        return HttpResponse.ok(HttpStatus.OK, "닉네임 수정이 완료되었습니다.");
    }

    @PatchMapping("/members/profile")
    public ResponseEntity<?> memberProfileModify(
            @AllowedContentType(allowedTypes = {"image/jpg", "image/jpeg", "image/png"},
                    allowedExtensions = {"jpg", "jpeg", "png"},
                    message = "jpg,jpeg,png 파일만 등록 가능합니다.")
            @RequestPart(value = "profile", required = true)
            MultipartFile profile,
            @CookieValue(value = "jwt", required = true)
            String token,
            HttpServletResponse httpServletResponse
    ) {
        String newToken = modifyMemberUseCase.modifyMemberProfile(token, profile);
        httpServletResponse.addCookie(bakeJwtCookie(newToken));
        return HttpResponse.ok(HttpStatus.OK, "프로필 사진 수정이 완료되었습니다.");
    }

    private Cookie bakeJwtCookie(String token) {
        Cookie cookie = new Cookie("jwt",token);
        cookie.setMaxAge(864000);
        return cookie;
    }

}

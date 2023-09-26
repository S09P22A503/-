package com.sshmarket.auth.auth.adapter.in.web;

import com.sshmarket.auth.auth.adapter.in.web.request.dto.RequestNicknameDto;
import com.sshmarket.auth.auth.adapter.in.web.request.dto.RequestProfileDto;
import com.sshmarket.auth.auth.adapter.in.web.request.valid.AllowedContentType;
import com.sshmarket.auth.auth.adapter.in.web.response.HttpResponse;
import com.sshmarket.auth.auth.adapter.in.web.util.CookieBaker;
import com.sshmarket.auth.auth.application.port.in.ModifyMemberUseCase;
import com.sshmarket.auth.auth.domain.Member;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
    private final CookieBaker cookieBaker;

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
    public ResponseEntity<?> memberNicknameModify(@Valid RequestNicknameDto requestNicknameDto, @CookieValue(value = "jwt", required = true) String token, HttpServletResponse httpServletResponse) {
        String newToken = modifyMemberUseCase.modifyMemberNickname(token,
                requestNicknameDto.getNickname());
        httpServletResponse.addCookie(cookieBaker.bakeJwtCookie(token));
        return HttpResponse.ok(HttpStatus.OK, "닉네임 수정이 완료되었습니다.");
    }

    @PatchMapping("/members/profile")
    public ResponseEntity<?> memberProfileModify(@Valid RequestProfileDto requestProfileDto, @CookieValue(value = "jwt", required = true) String token, HttpServletResponse httpServletResponse) {
        String newToken = modifyMemberUseCase.modifyMemberProfile(token, requestProfileDto.getProfile());
        httpServletResponse.addCookie(cookieBaker.bakeJwtCookie(token));
        return HttpResponse.ok(HttpStatus.OK, "프로필 사진 수정이 완료되었습니다.");
    }

}

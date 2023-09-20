package com.sshmarket.auth.auth.adapter.in.web;

import com.sshmarket.auth.auth.adapter.in.web.request.valid.AllowedContentType;
import com.sshmarket.auth.auth.application.port.in.ModifyMemberUseCase;
import com.sshmarket.auth.auth.domain.Member;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Component
@RestController
@RequiredArgsConstructor
public class MemberModifyController {

    private final ModifyMemberUseCase modifyMemberUseCase;

    @PatchMapping("/members/nickname")
    public ResponseEntity<?> memberNicknameModify(
            @NotNull(message = "사용자 정보가 없습니다.")
            Long id,

            @NotBlank(message = "닉네임을 입력해주세요.")
            @Size(min = 4, max = 20, message = "닉네임은 4자 이상 20자 이하로 입력해주세요.")
            String nickname
    ) {
        Member member = null;
        String result = modifyMemberUseCase.modifyMemberNickname(member);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("/members/profile")
    public ResponseEntity<?> memberProfileModify(
            @RequestParam
            @NotNull(message = "사용자 정보가 없습니다.")
            Long id,

            @AllowedContentType(allowedTypes = {"image/jpg", "image/jpeg", "image/png"},
                    allowedExtensions = {"jpg", "jpeg", "png"},
                    message = "jpg,jpeg,png 파일만 등록 가능합니다.")
            @RequestPart(value = "profile", required = true)
            MultipartFile profile
    ) {
        Member member = null;
        String result = modifyMemberUseCase.modifyMemberProfile(member, profile);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

package com.sshmarket.auth.auth.adapter.in.web.dto;

import com.sshmarket.auth.auth.adapter.in.web.request.valid.AllowedContentType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestRegisterDto {

    @NotBlank(message = "계정 정보를 불러올 수 없습니다.")
    private String code;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 4, max = 20, message = "닉네임은 4자 이상 20자 이하로 입력해주세요.")
    private String nickname;

    @AllowedContentType(allowedTypes = {"image/jpg", "image/jpeg", "image/png"},
            allowedExtensions = {"jpg", "jpeg", "png"},
            message = "jpg,jpeg,png 파일만 등록 가능합니다.")
    MultipartFile profile;



}

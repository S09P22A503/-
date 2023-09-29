package com.sshmarket.auth.auth.adapter.in.web.request.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;

@Data
public class RequestNicknameDto {

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 4, max = 20, message = "닉네임은 4자 이상 20자 이하로 입력해주세요.")
    private String nickname;

}

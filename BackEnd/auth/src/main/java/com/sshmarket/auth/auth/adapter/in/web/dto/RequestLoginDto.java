package com.sshmarket.auth.auth.adapter.in.web.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RequestLoginDto {

    @NotBlank(message = "계정 정보를 불러올 수 없습니다.")
    private String code;

}

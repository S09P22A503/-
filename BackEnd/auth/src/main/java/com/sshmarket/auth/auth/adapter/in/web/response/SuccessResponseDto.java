package com.sshmarket.auth.auth.adapter.in.web.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponseDto {

    private String message;
    private Object data;
}

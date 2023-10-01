package com.sshmarket.auth.auth.adapter.in.web.dto;

import com.sshmarket.auth.auth.domain.Member;
import lombok.Data;

@Data
public class ResponseDto {

    Long id;
    String nickname;
    String profile;
    String token;

    public static ResponseDto getMemberInfo(Member member) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.id = member.getId();
        responseDto.nickname = member.getNickname();
        responseDto.profile = member.getProfile();
        responseDto.token = member.getToken();
        return responseDto;
    }

    public String popToken() {
        String candidate = token;
        token = null;
        return candidate;
    }

    public void pushToken(String token) {
        this.token = token;
    }

}

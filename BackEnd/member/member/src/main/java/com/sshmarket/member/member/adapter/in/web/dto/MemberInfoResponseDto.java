package com.sshmarket.member.member.adapter.in.web.dto;


import com.sshmarket.member.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberInfoResponseDto {

    private long id;

    private String nickname;

    private String profile;

    public static MemberInfoResponseDto createFromMember(Member member) {
        MemberInfoResponseDto memberInfoResponseDto = new MemberInfoResponseDto();
        memberInfoResponseDto.id = member.getId();
        memberInfoResponseDto.nickname = member.getNickname();
        memberInfoResponseDto.profile = member.getProfile();
        return memberInfoResponseDto;
    }

}

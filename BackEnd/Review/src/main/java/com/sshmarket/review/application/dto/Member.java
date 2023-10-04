package com.sshmarket.review.application.dto;

import lombok.Getter;

@Getter
public class Member {

    private Long id;

    private String nickname;

    private String profile;

    private Member () {}

    public static Member createWithPublicInfo(Long id, String nickname, String profile) {
        Member member = new Member();
        member.id = id;
        member.nickname = nickname;
        member.profile = profile;
        return member;
    }

}
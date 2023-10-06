package com.sshmarket.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    Long id;

    String nickname;

    String profile;

    public static Member createWithPublicInfo(Long id, String nickname, String profile) {
        Member member = new Member();
        member.id = id;
        member.nickname = nickname;
        member.profile = profile;
        return member;
    }
}

package com.sshmarket.auth.auth.domain;

import lombok.Getter;

@Getter
public class Member {

    private Long id;

    private String nickname;

    private String profile;

    private String oauthId;

    private String email;

    private Member () {}

    public static Member createWithoutId(String nickname, String profile, String oauthId, String email) {
        Member member = new Member();
        member.nickname = nickname;
        member.profile = profile;
        member.oauthId = oauthId;
        member.email = email;
        return member;
    }

    public static Member createWithId(Long id, String nickname, String profile, String oauthId, String email) {
        Member member = new Member();
        member.id = id;
        member.nickname = nickname;
        member.profile = profile;
        member.oauthId = oauthId;
        member.email = email;
        return member;
    }

    public static Member createWithPublicInfo(Long id, String nickname, String profile) {
        Member member = new Member();
        member.id = id;
        member.nickname = nickname;
        member.profile = profile;
        return member;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changeProfile(String profile) {
        this.profile = profile;
    }

}

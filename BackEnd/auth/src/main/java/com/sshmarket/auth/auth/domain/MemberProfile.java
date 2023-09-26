package com.sshmarket.auth.auth.domain;

import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class MemberProfile {

    private MultipartFile file;

    private String name;

    private String url;

    private MemberProfile () {}

    public static MemberProfile createWithoutUrl(MultipartFile file) {
        MemberProfile memberProfile = new MemberProfile();
        memberProfile.file = file;
        memberProfile.name = createName();
        return memberProfile;
    }

    public static MemberProfile createFromMember(Member member) {
        MemberProfile memberProfile = new MemberProfile();
        String url = member.getProfile();
        String[] splitUrlBySlash = url.split("/");
        memberProfile.name = splitUrlBySlash[splitUrlBySlash.length-1].split("\\.")[0];
        memberProfile.url = member.getProfile();
        return memberProfile;
    }

    public void fillMemberProfileUrl(String url) {
        this.url = url;
    }

    private static String createName() {
        return UUID.randomUUID().toString();
    }

    private static String parseContentType(String contentType) {
        return contentType.split("")[1];
    }
}
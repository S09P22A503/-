package com.sshmarket.auth.auth.adapter.out.persistent.mapper;


import com.sshmarket.auth.auth.adapter.out.persistent.entity.DocMember;
import com.sshmarket.auth.auth.adapter.out.persistent.entity.RdbMember;
import com.sshmarket.auth.auth.domain.Member;

public class PersistMapper {

    public static DocMember RdbMemberToNoMember(RdbMember rdbMember) {
        return new DocMember(
                rdbMember.getId(),
                rdbMember.getNickname(),
                rdbMember.getProfile()
        );
    }

    public static RdbMember MemberToRdbMember(Member member) {
        return new RdbMember(
                member.getNickname(),
                member.getProfile(),
                member.getOauthId(),
                member.getEmail()
        );
    }

    public static Member RdbMemberToMember(RdbMember rdbMember) {
        return Member.createWithId(
                rdbMember.getId(),
                rdbMember.getNickname(),
                rdbMember.getProfile(),
                rdbMember.getOauthId(),
                rdbMember.getEmail()
        );
    }

}

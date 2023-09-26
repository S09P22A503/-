package com.sshmarket.member.member.adapter.out.persistent.mapper;


import com.sshmarket.member.member.adapter.out.persistent.entity.DocMember;
import com.sshmarket.member.member.domain.Member;

public class PersistMapper {

    public static Member DocMemberToMember(DocMember docMember) {
        if (docMember == null) return null;
        return Member.createWithId(docMember.getId(), docMember.getNickname(),
                docMember.getProfile());
    }

}

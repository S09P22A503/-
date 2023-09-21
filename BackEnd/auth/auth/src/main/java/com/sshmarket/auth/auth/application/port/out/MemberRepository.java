package com.sshmarket.auth.auth.application.port.out;

import com.sshmarket.auth.auth.domain.Member;

public interface MemberRepository {

    public boolean existsByEmail(String email);

    public boolean existsByNickname(String nickname);

    public Member findMemberById(Long id);

    public Member findMemberByEmail(String email);

    public Member saveMember(Member member);

    public Member updateMemberNickname(Member member);

    public Member updateMemberProfile(Member member);

}

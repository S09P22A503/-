package com.sshmarket.member.member.application.port.out;

import com.sshmarket.member.member.domain.Member;
import java.util.List;

public interface MemberRepository {

    public Member findMemberById(Long id);

    public List<Member> findMemberListByIdList(List<Long> idList);

            List<Member> findMemberList();

}

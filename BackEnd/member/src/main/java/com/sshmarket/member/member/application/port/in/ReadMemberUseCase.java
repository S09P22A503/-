package com.sshmarket.member.member.application.port.in;

import com.sshmarket.member.member.domain.Member;
import java.util.List;

public interface ReadMemberUseCase {

    public Member findMemberById(Long id);

    public List<Member> findMemberListByIdList(List<Long> idList);

    public List<Member> findMemberList();

}

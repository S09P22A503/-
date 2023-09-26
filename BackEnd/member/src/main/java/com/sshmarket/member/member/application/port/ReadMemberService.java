package com.sshmarket.member.member.application.port;

import com.sshmarket.member.member.application.port.in.ReadMemberUseCase;
import com.sshmarket.member.member.application.port.out.MemberRepository;
import com.sshmarket.member.member.domain.Member;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadMemberService implements ReadMemberUseCase {

    private final MemberRepository memberRepository;

    @Override
    public Member findMemberById(Long id) {
        return memberRepository.findMemberById(id);
    }

    @Override
    public List<Member> findMemberListByIdList(List<Long> idList) {
        return memberRepository.findMemberListByIdList(idList);
    }
}

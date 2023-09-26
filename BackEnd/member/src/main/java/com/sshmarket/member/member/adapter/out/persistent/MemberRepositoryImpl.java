package com.sshmarket.member.member.adapter.out.persistent;

import com.sshmarket.member.member.adapter.out.persistent.entity.DocMember;
import com.sshmarket.member.member.adapter.out.persistent.mapper.PersistMapper;
import com.sshmarket.member.member.adapter.out.persistent.repository.DocMemberRepository;
import com.sshmarket.member.member.application.port.out.MemberRepository;
import com.sshmarket.member.member.domain.Member;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final DocMemberRepository docMemberRepository;

    @Override
    public Member findMemberById(Long id) {
        return PersistMapper.DocMemberToMember(docMemberRepository.findById(id).orElse(null));
    }

    @Override
    public List<Member> findMemberListByIdList(List<Long> idList) {
        List<Member> memberList = ((List<DocMember>) docMemberRepository.findAllById(idList))
                .stream()
                .map(docMember -> PersistMapper.DocMemberToMember(docMember))
                .collect(Collectors.toList());
        return memberList;
    }
}

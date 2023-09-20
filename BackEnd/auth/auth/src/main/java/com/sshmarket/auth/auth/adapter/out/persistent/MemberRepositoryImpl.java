package com.sshmarket.auth.auth.adapter.out.persistent;

import com.sshmarket.auth.auth.adapter.out.persistent.entity.DocMember;
import com.sshmarket.auth.auth.adapter.out.persistent.entity.RdbMember;
import com.sshmarket.auth.auth.adapter.out.persistent.mapper.PersistMapper;
import com.sshmarket.auth.auth.adapter.out.persistent.repository.DocMemberRepository;
import com.sshmarket.auth.auth.adapter.out.persistent.repository.RdbMemberRepository;
import com.sshmarket.auth.auth.application.port.out.MemberRepository;
import com.sshmarket.auth.auth.domain.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final RdbMemberRepository rdbMemberRepository;
    private final DocMemberRepository docMemberRepository;

    @Override
    public boolean existsByEmail(String email) {
        return rdbMemberRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return rdbMemberRepository.existsByNickname(nickname);
    }

    @Override
    public Member findByEmail(String email) {
        Optional<RdbMember> rdbMember = rdbMemberRepository.findByEmail(email);
        if (rdbMember.isEmpty()) return null;
        return PersistMapper.RdbMemberToMember(rdbMember.get());
    }

    @Override
    public Member save(Member member) {
        RdbMember rdbMember = PersistMapper.MemberToRdbMember(member);
        rdbMemberRepository.save(rdbMember);
        DocMember docMember = PersistMapper.RdbMemberToNoMember(rdbMember);
        docMemberRepository.save(docMember);
        return PersistMapper.RdbMemberToMember(rdbMember);
    }

    @Override
    public Member update(Member member) {
        return null;
    }
}

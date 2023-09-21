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
    public Member findMemberById(Long id) {
        Optional<RdbMember> rdbMember = rdbMemberRepository.findById(id);
        if (rdbMember.isEmpty()) return null;
        return PersistMapper.RdbMemberToMember(rdbMember.get());
    }

    @Override
    public Member findMemberByEmail(String email) {
        Optional<RdbMember> rdbMember = rdbMemberRepository.findByEmail(email);
        if (rdbMember.isEmpty()) return null;
        return PersistMapper.RdbMemberToMember(rdbMember.get());
    }

    @Override
    public Member saveMember(Member member) {
        RdbMember rdbMember = PersistMapper.MemberToRdbMember(member);
        rdbMemberRepository.save(rdbMember);
        DocMember docMember = PersistMapper.RdbMemberToNoMember(rdbMember);
        docMemberRepository.save(docMember);
        return PersistMapper.RdbMemberToMember(rdbMember);
    }

    @Override
    public Member updateMemberNickname(Member member) {
        Optional<RdbMember> checkRdb = rdbMemberRepository.findById(member.getId());
        Optional<DocMember> checkDoc = docMemberRepository.findById(member.getId());
        if (checkRdb.isEmpty() || checkDoc.isEmpty()) {
            return null;
        }
        RdbMember rdbMember = checkRdb.get();
        DocMember docMember = checkDoc.get();
        docMemberRepository.delete(checkDoc.get());
        rdbMember.updateNickname(member.getNickname());
        docMember.updateNickname(member.getNickname());
        docMemberRepository.save(docMember);
        return PersistMapper.RdbMemberToMember(rdbMember);
    }

    @Override
    public Member updateMemberProfile(Member member) {
        Optional<RdbMember> checkRdb = rdbMemberRepository.findById(member.getId());
        Optional<DocMember> checkDoc = docMemberRepository.findById(member.getId());
        if (checkRdb.isEmpty() || checkDoc.isEmpty()) {
            return null;
        }
        RdbMember rdbMember = checkRdb.get();
        DocMember docMember = checkDoc.get();
        docMemberRepository.delete(checkDoc.get());
        rdbMember.updateProfile(member.getProfile());
        docMember.updateProfile(member.getProfile());
        docMemberRepository.save(docMember);
        return PersistMapper.RdbMemberToMember(rdbMember);
    }
}

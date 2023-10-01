package com.sshmarket.auth.auth.application.port;

import com.sshmarket.auth.auth.application.port.in.ModifyMemberUseCase;
import com.sshmarket.auth.auth.application.port.out.MemberProfileRepository;
import com.sshmarket.auth.auth.application.port.out.MemberRepository;
import com.sshmarket.auth.auth.application.port.util.JwtAdmin;
import com.sshmarket.auth.auth.application.port.util.JwtTranslator;
import com.sshmarket.auth.auth.domain.Member;
import com.sshmarket.auth.auth.domain.MemberProfile;
import com.sshmarket.auth.auth.exception.BusinessException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyMemberService implements ModifyMemberUseCase {

    private final JwtAdmin jwtAdmin;
    private final JwtTranslator jwtTranslator;
    private final MemberRepository memberRepository;
    private final MemberProfileRepository memberProfileRepository;

    @Override
    public boolean existCheckMemberNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    @Override
    public Member modifyMemberNickname(String token, String nickname) {
        Member member = jwtTranslator.translate(token);
        if (memberRepository.existsByNickname(nickname)) {
            throw new BusinessException("이미 존재하는 닉네임입니다.");
        }
        if (member == null) {
            throw new BusinessException("잘못된 회원정보입니다.");
        }
        member.changeNickname(nickname);
        memberRepository.updateMemberNickname(member);
        member.fillToken(jwtAdmin.generateToken(member));
        return member;
    }

    @Override
    public Member modifyMemberProfile(String token, MultipartFile profileFile) {
        if (profileFile.isEmpty()) {
            throw new BusinessException("파일이 정상적으로 업로드되지 않았습니다.");
        }

        Member member = jwtTranslator.translate(token);
        if (member == null) {
            throw new BusinessException("잘못된 회원정보입니다.");
        }
        MemberProfile beforeProfile = null;
        if (member.getProfile() != null) {
            beforeProfile = MemberProfile.createFromMember(member);
        }

        MemberProfile afterProfile = MemberProfile.createWithoutUrl(profileFile);
        afterProfile = memberProfileRepository.saveMemberProfile(afterProfile);
        if (afterProfile.getUrl() == null) {
            throw new BusinessException("프로필 사진 수정 중 문제가 발생했습니다.");
        }
        member.changeProfile(afterProfile.getUrl());
        memberRepository.updateMemberProfile(member);

        memberProfileRepository.removeMemberProfile(beforeProfile);

        member.fillToken(jwtAdmin.generateToken(member));

        return member;
    }
}

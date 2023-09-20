package com.sshmarket.auth.auth.application.port;

import com.sshmarket.auth.auth.application.port.in.ModifyMemberUseCase;
import com.sshmarket.auth.auth.application.port.out.MemberRepository;
import com.sshmarket.auth.auth.application.port.util.JwtAdmin;
import com.sshmarket.auth.auth.domain.Member;
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
    private final MemberRepository memberRepository;

    @Override
    public boolean existCheckMemberNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    @Override
    public String modifyMemberNickname(Long id, String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new BusinessException("이미 존재하는 닉네임입니다.");
        }
        Member member = memberRepository.update(Member.createWithIdandNickname(id, nickname));
        if (member == null) {
            throw new BusinessException("잘못된 회원정보입니다.");
        }
        return jwtAdmin.generateToken(member);
    }

    @Override
    public String modifyMemberProfile(Long id, MultipartFile profileFile) {
        // 새로운 프로필 파일 업로드 및 기존 파일 삭제 후 url 받는 로직 필요
        String profile = profileFile.getOriginalFilename();
        Member member = memberRepository.update(Member.createWithIdandProfile(id, profile));
        if (member == null) {
            throw new BusinessException("잘못된 회원정보입니다.");
        }
        return jwtAdmin.generateToken(member);
    }
}

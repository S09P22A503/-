package com.sshmarket.auth.auth.application.port;

import com.fasterxml.jackson.databind.JsonNode;
import com.sshmarket.auth.auth.application.port.in.SignupUseCase;
import com.sshmarket.auth.auth.application.port.out.MemberProfileRepository;
import com.sshmarket.auth.auth.application.port.out.MemberRepository;
import com.sshmarket.auth.auth.application.port.util.JwtAdmin;
import com.sshmarket.auth.auth.application.port.util.OauthConnector;
import com.sshmarket.auth.auth.domain.Member;
import com.sshmarket.auth.auth.domain.MemberProfile;
import com.sshmarket.auth.auth.exception.BusinessException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class SignupService implements SignupUseCase {

    private final JwtAdmin jwtAdmin;
    private final OauthConnector oauthConnector;
    private final MemberRepository memberRepository;
    private final MemberProfileRepository memberProfileRepository;

    @Override
    public String signup(String code, String nickname, MultipartFile profileFile) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new BusinessException("이미 존재하는 닉네임입니다.");
        }
        String accessToken = oauthConnector.getAccessToken(code);
        JsonNode memberResourceNode = oauthConnector.getMemberResource(accessToken);
        String oauthId = memberResourceNode.get("id").asText();
        String email = memberResourceNode.get("email").asText();
        if (memberRepository.existsByEmail(email)) {
            throw new BusinessException("이미 가입한 계정입니다.");
        }
        MemberProfile memberProfile = MemberProfile.createWithoutUrl(profileFile);
        memberProfile = memberProfileRepository.saveMemberProfile(memberProfile);
        String profile = null;
        if (!profileFile.isEmpty()) {
            if (memberProfile == null) {
                throw new BusinessException("프로필 사진 업로드 중 문제가 발생했습니다.");
            }
            profile = memberProfile.getUrl();
        }
        Member member = Member.createWithoutId(nickname, profile, oauthId, email);
        member = memberRepository.saveMember(member);
        return jwtAdmin.generateToken(member);
    }
}

package com.sshmarket.auth.auth.application.port;

import com.fasterxml.jackson.databind.JsonNode;
import com.sshmarket.auth.auth.application.port.in.LoginUseCase;
import com.sshmarket.auth.auth.application.port.out.MemberRepository;
import com.sshmarket.auth.auth.application.port.util.JwtAdmin;
import com.sshmarket.auth.auth.application.port.util.OauthConnector;
import com.sshmarket.auth.auth.domain.Member;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final JwtAdmin jwtAdmin;
    private final OauthConnector oauthConnector;
    private final MemberRepository memberRepository;

    @Override
    public String login(String code) {
        String accessToken = oauthConnector.getAccessToken(code);
        JsonNode memberResourceNode = oauthConnector.getMemberResource(accessToken);
        Member member = memberRepository.findMemberByEmail(memberResourceNode.get("email").asText());
        if (member == null) return null;
        return jwtAdmin.generateToken(member);
    }
}

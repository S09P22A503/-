package com.sshmarket.auth.auth.application.port.in;

import com.sshmarket.auth.auth.domain.Member;
import org.springframework.web.multipart.MultipartFile;

public interface ModifyMemberUseCase {

    public boolean existCheckMemberNickname(String nickname);

    public Member modifyMemberNickname(String token, String nickname);

    public Member modifyMemberProfile(String token, MultipartFile profile);


}

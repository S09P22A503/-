package com.sshmarket.auth.auth.application.port.in;

import com.sshmarket.auth.auth.domain.Member;
import org.springframework.web.multipart.MultipartFile;

public interface ModifyMemberUseCase {

    public String modifyMemberNickname(Member member);

    public String modifyMemberProfile(Member member, MultipartFile profile);


}

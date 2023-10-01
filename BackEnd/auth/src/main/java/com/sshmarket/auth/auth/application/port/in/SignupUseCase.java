package com.sshmarket.auth.auth.application.port.in;

import com.sshmarket.auth.auth.domain.Member;
import org.springframework.web.multipart.MultipartFile;

public interface SignupUseCase {

    public Member signup(String code, String nickname, MultipartFile profile);

}

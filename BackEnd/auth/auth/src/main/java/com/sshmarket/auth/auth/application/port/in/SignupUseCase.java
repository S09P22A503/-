package com.sshmarket.auth.auth.application.port.in;

import org.springframework.web.multipart.MultipartFile;

public interface SignupUseCase {

    public String signup(String code, String nickname, MultipartFile profile);

}

package com.sshmarket.auth.auth.application.port.in;

import com.sshmarket.auth.auth.domain.Member;

public interface LoginUseCase {
    public Member login(String code);

}

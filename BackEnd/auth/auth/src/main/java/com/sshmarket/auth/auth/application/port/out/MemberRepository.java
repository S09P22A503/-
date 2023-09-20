package com.sshmarket.auth.auth.application.port.out;

import com.sshmarket.auth.auth.domain.Member;
import java.util.Optional;

public interface MemberRepository {

    public boolean existsByEmail(String email);

    public boolean existsByNickname(String nickname);

    public Member findByEmail(String email);

    public Member save(Member member);

    public Member update(Member member);

}

package com.sshmarket.auth.auth.adapter.out.persistent.repository;

import com.sshmarket.auth.auth.adapter.out.persistent.entity.RdbMember;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RdbMemberRepository extends JpaRepository<RdbMember, Long> {

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Optional<RdbMember> findByEmail(String email);

    @Override
    Optional<RdbMember> findById(Long id);
}

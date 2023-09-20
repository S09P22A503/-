package com.sshmarket.auth.auth.adapter.out.persistent.repository;

import com.sshmarket.auth.auth.adapter.out.persistent.entity.DocMember;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocMemberRepository extends MongoRepository<DocMember, Long> {

    @Override
    Optional<DocMember> findById(Long id);


}

package com.sshmarket.auth.auth.application.port;

import com.sshmarket.auth.auth.application.port.in.ModifyMemberUseCase;
import com.sshmarket.auth.auth.domain.Member;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ModifyMemberService implements ModifyMemberUseCase {

    @Override
    public String modifyMemberNickname(Member member) {
        return null;
    }

    @Override
    public String modifyMemberProfile(Member member, MultipartFile profile) {
        return null;
    }


}

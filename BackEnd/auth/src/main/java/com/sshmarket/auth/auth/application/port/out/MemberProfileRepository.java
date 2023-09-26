package com.sshmarket.auth.auth.application.port.out;

import com.sshmarket.auth.auth.domain.MemberProfile;

public interface MemberProfileRepository {

    public MemberProfile saveMemberProfile(MemberProfile memberProfile);

    public MemberProfile removeMemberProfile (MemberProfile memberProfile);

}

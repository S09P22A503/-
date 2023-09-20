package com.sshmarket.auth.auth.adapter.out.persistent.entity;

import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "member")
@NoArgsConstructor
@Getter
public class DocMember {

    @Id
    private long id;

    private String nickname;

    private String profile;

    public DocMember(Long id, String nickname, String profile) {
        this.id = id;
        this.nickname = nickname;
        this.profile = profile;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateProfile(String profile) {
        this.profile = profile;
    }

}

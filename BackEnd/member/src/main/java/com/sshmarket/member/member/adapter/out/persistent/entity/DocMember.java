package com.sshmarket.member.member.adapter.out.persistent.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
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

}

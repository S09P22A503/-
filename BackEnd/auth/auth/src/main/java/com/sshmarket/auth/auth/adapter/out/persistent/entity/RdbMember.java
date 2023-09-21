package com.sshmarket.auth.auth.adapter.out.persistent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "member")
public class RdbMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String nickname;

    @Column(length = 1000)
    private String profile;

    @Column(length = 300)
    private String oauthId;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    public RdbMember(String nickname, String profile, String oauthId, String email) {
        this.nickname = nickname;
        this.profile = profile;
        this.oauthId = oauthId;
        this.email = email;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateProfile(String profile) {
        this.profile = profile;
    }
}

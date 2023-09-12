package com.sshmarket.article.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ArticleBookmark {
    @Id
    @Column(name = "article_bookmark_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;
}

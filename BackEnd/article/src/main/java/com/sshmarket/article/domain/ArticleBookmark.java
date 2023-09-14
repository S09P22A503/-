package com.sshmarket.article.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleBookmark {
    @Id
    @Column(name = "article_bookmark_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Builder
    private ArticleBookmark(Long memberId, Article article) {
        this.memberId = memberId;
        this.article = article;
    }

    public static ArticleBookmark createArticleBookmark(Long memberId, Article article) {
        ArticleBookmark bookmark = ArticleBookmark.builder()
                .memberId(memberId)
                .article(article)
                .build();

        article.addArticleBookmark(bookmark);

        return bookmark;
    }

    public void removeArticleBookmark(ArticleBookmark articleBookmark) {
        this.article.removeArticleBookmark(articleBookmark);
    }
}

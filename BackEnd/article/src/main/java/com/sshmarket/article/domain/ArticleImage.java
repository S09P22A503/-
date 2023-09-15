package com.sshmarket.article.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleImage {
    @Id
    @Column(name = "article_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Builder
    private ArticleImage(String imageUrl, Article article) {
        this.imageUrl = imageUrl;
        this.article = article;
    }

    public static ArticleImage createArticleImage(String imageUrl, Article article){
        ArticleImage articleImage = ArticleImage.builder()
                .imageUrl(imageUrl)
                .article(article)
                .build();

        return articleImage;
    }
}

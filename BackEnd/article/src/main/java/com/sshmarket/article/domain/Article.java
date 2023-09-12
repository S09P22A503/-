package com.sshmarket.article.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(nullable = false)
    private Integer price;

    private Integer amount;

    private Integer mass;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    private String mainImage;

    @ColumnDefault("false")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "article")
    private List<ArticleImage> articleImages = new ArrayList<>();

    @OneToMany(mappedBy = "article")
    private List<ArticleBookmark> articleBookmarks = new ArrayList<>();

    @Builder
    private Article(Long memberId, Integer price, Integer amount, Integer mass, String title, String content, String mainImage) {
        this.memberId = memberId;
        this.price = price;
        this.amount = amount;
        this.mass = mass;
        this.title = title;
        this.content = content;
        this.mainImage = mainImage;
    }

    public static Article createArticle(Long memberId, Integer price, Integer amount, Integer mass, String title, String content, String mainImage, List<String> imageUrls) {
        Article article = Article.builder()
                .memberId(memberId)
                .price(price)
                .amount(amount)
                .mass(mass)
                .title(title)
                .content(content)
                .mainImage(mainImage)
                .build();

        article.addArticleImage(imageUrls, article);
        return article;
    }

    public void addArticleImage(List<String> imageUrls, Article article) {
        for (String url: imageUrls) {
            this.articleImages.add(ArticleImage.createArticleImage(url, article));
        }
    }
}

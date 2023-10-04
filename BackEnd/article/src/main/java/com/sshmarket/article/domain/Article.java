package com.sshmarket.article.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TradeType tradeType;

    @Column(nullable = false)
    private String mainImage;

    @ColumnDefault("false")
    @Column(insertable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "article")
    private List<ArticleImage> articleImages = new ArrayList<>();

    @OneToMany(mappedBy = "article")
    private List<ArticleBookmark> articleBookmarks = new ArrayList<>();

    @Builder
    private Article(Long memberId, Product product, Integer price, Integer amount, Integer mass, String title, String content, String mainImage, Location location, TradeType tradeType) {
        this.memberId = memberId;
        this.product = product;
        this.price = price;
        this.amount = amount;
        this.mass = mass;
        this.title = title;
        this.content = content;
        this.mainImage = mainImage;
        this.location = location;
        this.tradeType = tradeType;
    }

    public static Article createArticle(Long memberId, Product product, Location location, Integer price, Integer amount, Integer mass, TradeType tradeType, String title, String content, String mainImage, List<String> imageUrls) {
        Article article = Article.builder()
                .memberId(memberId)
                .price(price)
                .amount(amount)
                .mass(mass)
                .title(title)
                .content(content)
                .location(location)
                .product(product)
                .mainImage(mainImage)
                .tradeType(tradeType)
                .build();

        product.addArticle(article);
        location.addArticle(article);
        article.addArticleImage(imageUrls, article);
        return article;
    }

    public void addArticleImage(List<String> imageUrls, Article article) {
        for (String url: imageUrls) {
            this.articleImages.add(ArticleImage.createArticleImage(url, article));
        }
    }

    public void addArticleBookmark(ArticleBookmark articleBookmark) {
        this.articleBookmarks.add(articleBookmark);
    }

    public void modifyArticle(Article modifiedArticle) {
        Article originArticle = this;
        this.price = modifiedArticle.price;
        this.title = modifiedArticle.title;
        this.content = modifiedArticle.content;
        this.mainImage = modifiedArticle.mainImage; //메인 이미지 변경->원래 이미지 삭제하고 올리는 로직 추후에 추가

        // 지역과 상품 정보가 변경되었으면 리스트에서 삭제하고 새로 추가
        if(this.location != modifiedArticle.location) {
            this.location.removeArticle(originArticle);
            this.location = modifiedArticle.location;
            this.location.addArticle(modifiedArticle);
        }
        if(this.product != modifiedArticle.product) {
            this.product.removeArticle(originArticle);
            this.product = modifiedArticle.product;
            this.product.addArticle(modifiedArticle);
        }
    }

    public void removeArticle(Article article) {
        article.isDeleted = true;
        this.product.removeArticle(article);
        this.location.removeArticle(article);
    }

    public void removeArticleImage(ArticleImage target) {
        this.articleImages.removeIf(image -> image.equals(target));
    }

    public void removeArticleBookmark(ArticleBookmark target) {
        this.articleBookmarks.removeIf(bookmark -> bookmark.equals(target));
    }

}

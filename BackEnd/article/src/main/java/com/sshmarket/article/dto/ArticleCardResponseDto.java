package com.sshmarket.article.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ArticleCardResponseDto {

    private Long id;

    private String mainImageUrl;

    private String title;

    private Integer price;

    private Integer amount;

    private Integer mass;

    private Double starRating;

    private Integer reviewCnt;

    @Builder
    public ArticleCardResponseDto(Long id, String mainImageUrl, String title, Integer price, Integer amount, Integer mass, Double starRating, Integer reviewCnt) {
        this.id = id;
        this.mainImageUrl = mainImageUrl;
        this.title = title;
        this.price = price;
        this.amount = amount;
        this.mass = mass;
        this.starRating = starRating;
        this.reviewCnt = reviewCnt;
    }
}

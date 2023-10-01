package com.sshmarket.article.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReviewResponseDto {

    Long articleId;

    Double starRating;

    Integer reviewCnt;

    public ReviewResponseDto(Long articleId, Double starRating, Integer reviewCnt) {
        this.articleId = articleId;
        this.starRating = starRating;
        this.reviewCnt = reviewCnt;
    }
}

package com.sshmarket.article.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
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

package com.sshmarket.article.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@NoArgsConstructor
@Setter
public class ReviewRatingAndNum {

    Long articleId;

    Double starRating;

    Integer reviewCnt;

    public ReviewRatingAndNum(Long articleId, Double starRating, Integer reviewCnt) {
        this.articleId = articleId;
        this.starRating = starRating;
        this.reviewCnt = reviewCnt;
    }
}

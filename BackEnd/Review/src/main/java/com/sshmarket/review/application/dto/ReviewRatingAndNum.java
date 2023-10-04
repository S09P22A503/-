package com.sshmarket.review.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRatingAndNum {

    private Long articleId;
    private Double starRating;
    private Integer reviewCnt;

}

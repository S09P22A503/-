package com.sshmarket.review.application.port.in.query;

import com.sshmarket.review.application.feign.Article;
import com.sshmarket.review.application.feign.Member;
import com.sshmarket.review.domain.ReviewImage;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewResponse {

    private Long id;

    private Member member;

    private Article article;

    private Long buyHistoryId;

    private String message;

    private int starRating;

    private LocalDateTime createdAt;

    private List<ReviewImage> images;

}

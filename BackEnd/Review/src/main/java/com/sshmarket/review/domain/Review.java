package com.sshmarket.review.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Review {

    @Getter
    private final Long id;

    @Getter
    @NonNull
    private final Long memberId;

    @Getter
    @NonNull
    private final Long articleId;

    @Getter
    @NonNull
    private final Long buyHistoryId;

    @Getter
    @NonNull
    private final String message;

    @Getter
    @NonNull
    private final int starRating;

    @Getter
    @NonNull
    private final LocalDateTime createdAt;

    @Getter
    private final List<ReviewImage> reviewImages = new ArrayList<>();

    public static Review createReviewWithoutId(Long memberId, Long articleId, Long buyHistoryId,
            String message, int starRating) {
        return Review.builder()
                     .memberId(memberId)
                     .articleId(articleId)
                     .buyHistoryId(buyHistoryId)
                     .message(message)
                     .starRating(starRating)
                     .build();
    }

    public static Review createReviewWithId(Long id, Long memberId, Long articleId,
            Long buyHistoryId,
            String message, int starRating, List<ReviewImage> reviewImages) {

        Review review = Review.builder()
                              .id(id)
                              .memberId(memberId)
                              .articleId(articleId)
                              .buyHistoryId(buyHistoryId)
                              .message(message)
                              .starRating(starRating)
                              .build();

        review.addReviewImages(reviewImages);

        return review;
    }

    public void addReviewImages(List<ReviewImage> reviewImages) {
        this.reviewImages.addAll(reviewImages);
    }


}

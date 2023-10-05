package com.sshmarket.review.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Setter
public class Review {

    @Getter
    private Long id;

    @Getter
    private Long memberId;

    @Getter
    private Long articleId;

    @Getter
    private Long buyHistoryId;

    @Getter
    private String message;

    @Getter
    private int starRating;

    @Getter
    private LocalDateTime createdAt;

    @Getter
    private List<ReviewImage> reviewImages = new ArrayList<>();

    public static Review createReviewWithoutId(Long memberId, Long articleId, Long buyHistoryId,
            String message, int starRating) {
        return Review.builder()
                     .memberId(memberId)
                     .articleId(articleId)
                     .buyHistoryId(buyHistoryId)
                     .message(message)
                     .starRating(starRating)
                     .reviewImages(new ArrayList<>())
                     .build();
    }

    public static Review createReviewWithId(Long id, Long memberId, Long articleId,
            Long buyHistoryId,
            String message, int starRating, LocalDateTime createdAt,
            List<ReviewImage> reviewImages) {

        Review review = Review.builder()
                              .id(id)
                              .memberId(memberId)
                              .articleId(articleId)
                              .buyHistoryId(buyHistoryId)
                              .message(message)
                              .starRating(starRating)
                              .createdAt(createdAt)
                              .reviewImages(new ArrayList<>())
                              .build();

        review.addReviewImages(reviewImages);

        return review;
    }

    public void addReviewImages(List<ReviewImage> reviewImages) {
        this.reviewImages.addAll(reviewImages);
    }

    public void modifyReview(String message, int starRating, List<Long> savedReviewIds,
            List<ReviewImage> newReviewImages) {
        if (message != null) {
            setMessage(message);
        }

        if (starRating != 0) {
            setStarRating(starRating);
        }

        modifyReviewImages(savedReviewIds, newReviewImages);

    }

    private void modifyReviewImages(List<Long> savedReviewIds,
            List<ReviewImage> newReviewImages) {

        this.reviewImages.removeIf(
                reviewImage -> !savedReviewIds.contains(reviewImage.getReviewId()));

        this.addReviewImages(newReviewImages);
    }


}

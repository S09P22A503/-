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
    private final List<ReviewImage> images = new ArrayList<>();

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

    public void addReviewImages(List<ReviewImage> images) {
        this.images.addAll(images);
    }


}

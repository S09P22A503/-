package com.sshmarket.review.domain;

import com.sshmarket.review.application.port.in.AddReviewCommand;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

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
    private List<ReviewImage> images;

    public static Review createReviewWithoutId(Long memberId, Long articleId, Long buyHistoryId,
            String message, int starRating, List<ReviewImage> images) {
        return Review.builder()
                     .id(null)
                     .articleId(articleId)
                     .buyHistoryId(buyHistoryId)
                     .message(message)
                     .starRating(starRating)
                     .createdAt(null)
                     .images(images)
                     .build();
    }

    public static Review createReviewWithId(Long id, Long memberId, Long articleId,
            Long buyHistoryId,
            String message, int starRating, LocalDateTime createdAt, List<ReviewImage> images) {
        return Review.builder()
                     .id(id)
                     .articleId(articleId)
                     .buyHistoryId(buyHistoryId)
                     .message(message)
                     .starRating(starRating)
                     .createdAt(createdAt)
                     .images(images)
                     .build();
    }

}

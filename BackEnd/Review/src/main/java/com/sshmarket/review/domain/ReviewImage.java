package com.sshmarket.review.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewImage {

    @Getter
    private final Long id;

    @Getter
    @NonNull
    private final Long reviewId;

    @Getter
    @NonNull
    private final String imageUrl;

    public static ReviewImage createReviewImageWithoutId(Long reviewId, String imageUrl) {
        return new ReviewImage(null, reviewId, imageUrl);
    }

    public static ReviewImage createReviewImageWithId(Long id, Long reviewId, String imageUrl) {
        return new ReviewImage(id, reviewId, imageUrl);
    }
}

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
    private final String imageUrl;

    public static ReviewImage createReviewImageWithoutId(String imageUrl){
        return new ReviewImage(null, imageUrl);
    }

    public static ReviewImage createReviewImageWithId(Long id, String imageUrl){
        return new ReviewImage(id, imageUrl);
    }
}

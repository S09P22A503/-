package com.sshmarket.review.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
    private LocalDateTime createdAt;


}

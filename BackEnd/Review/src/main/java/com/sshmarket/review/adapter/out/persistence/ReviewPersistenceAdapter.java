package com.sshmarket.review.adapter.out.persistence;

import com.sshmarket.review.application.port.out.LoadReviewPort;
import com.sshmarket.review.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class ReviewPersistenceAdapter implements LoadReviewPort {
    private final JPAReviewRepository reviewRepository;

}

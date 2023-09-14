package com.sshmarket.review.adapter.out.persistence.review;

import com.sshmarket.review.application.port.out.InsertReviewPort;
import com.sshmarket.review.application.port.out.LoadReviewPort;
import com.sshmarket.review.common.PersistenceAdapter;
import com.sshmarket.review.domain.Review;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class ReviewPersistenceAdapter implements LoadReviewPort, InsertReviewPort {

    private final JPAReviewRepository reviewRepository;

    @Override
    public Review insertReview(Review review) {
        JPAReviewEntity newReview = JPAReviewEntity.from(review);
        JPAReviewEntity savedReview = reviewRepository.save(newReview);

        return savedReview.convertToDomain();
    }
}

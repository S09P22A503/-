package com.sshmarket.review.adapter.out.persistence;

import com.sshmarket.review.application.port.out.InsertReviewPort;
import com.sshmarket.review.application.port.out.LoadReviewPort;
import com.sshmarket.review.common.PersistenceAdapter;
import com.sshmarket.review.domain.Review;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class ReviewPersistenceAdapter implements LoadReviewPort, InsertReviewPort {

    private final JPAReviewRepository reviewRepository;
    private final JPAReviewImageRepository jpaReviewImageRepository;

    @Override
    public Review insertReview(Review review) {
        JPAReviewEntity newReview = JPAReviewEntity.from(review);

        List<JPAReviewImageEntity> jpaReviewImageEntities = jpaReviewImageRepository.saveAll(
                review.getImages()
                      .stream()
                      .map(JPAReviewImageEntity::from)
                      .collect(
                              Collectors.toList()));

        newReview.addReviewImages(jpaReviewImageEntities);

        JPAReviewEntity savedReview = reviewRepository.save(newReview);

        return savedReview.convertToDomain();
    }
}

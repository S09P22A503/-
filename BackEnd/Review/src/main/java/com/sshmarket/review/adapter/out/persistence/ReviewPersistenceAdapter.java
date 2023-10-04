package com.sshmarket.review.adapter.out.persistence;

import com.sshmarket.review.application.port.out.LoadReviewPort;
import com.sshmarket.review.application.port.out.SaveReviewPort;
import com.sshmarket.review.application.port.out.UpdateReviewPort;
import com.sshmarket.review.common.PersistenceAdapter;
import com.sshmarket.review.domain.Review;
import com.sshmarket.review.exception.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class ReviewPersistenceAdapter implements SaveReviewPort, UpdateReviewPort {

    private final JPAReviewRepository reviewRepository;
    private final JPAReviewImageRepository jpaReviewImageRepository;

    @Override
    public Review saveReview(Review review) {
        JPAReviewEntity newReview = JPAReviewEntity.from(review);

        JPAReviewEntity savedReview = reviewRepository.save(newReview);

        List<JPAReviewImageEntity> jpaReviewImageEntities = jpaReviewImageRepository.saveAll(
                review.getReviewImages()
                      .stream()
                      .map(reviewImage -> JPAReviewImageEntity.from(reviewImage, savedReview))
                      .collect(
                              Collectors.toList()));

        newReview.addReviewImages(jpaReviewImageEntities);

        return savedReview.convertToDomain();
    }


    @Override
    public void updateReview(Review review) {
        JPAReviewEntity oldReview = JPAReviewEntity.from(review);

        List<JPAReviewImageEntity> jpaReviewImageEntities = jpaReviewImageRepository.saveAll(
                review.getReviewImages()
                      .stream()
                      .map(reviewImage -> JPAReviewImageEntity.from(reviewImage, oldReview))
                      .collect(
                              Collectors.toList()));

        oldReview.addReviewImages(jpaReviewImageEntities);

    }
}

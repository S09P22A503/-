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
class ReviewPersistenceAdapter implements LoadReviewPort, SaveReviewPort, UpdateReviewPort {

    private final JPAReviewRepository reviewRepository;
    private final JPAReviewImageRepository jpaReviewImageRepository;

    @Override
    public Review saveReview(Review review) {
        JPAReviewEntity newReview = JPAReviewEntity.from(review);

        List<JPAReviewImageEntity> jpaReviewImageEntities = jpaReviewImageRepository.saveAll(
                review.getReviewImages()
                      .stream()
                      .map(JPAReviewImageEntity::from)
                      .collect(
                              Collectors.toList()));

        newReview.addReviewImages(jpaReviewImageEntities);

        JPAReviewEntity savedReview = reviewRepository.save(newReview);

        return savedReview.convertToDomain();
    }

    @Override
    public Review findReviewById(Long id) {
        JPAReviewEntity savedReview = reviewRepository.findById(id)
                                                      .orElseThrow(() -> new NotFoundException(
                                                              "존재하지 않는 리뷰 ID 입니다."));

        return savedReview.convertToDomain();
    }

    @Override
    public void updateReview(Review review) {
        JPAReviewEntity oldReview = JPAReviewEntity.from(review);

        List<JPAReviewImageEntity> jpaReviewImageEntities = jpaReviewImageRepository.saveAll(
                review.getReviewImages()
                      .stream()
                      .map(JPAReviewImageEntity::from)
                      .collect(
                              Collectors.toList()));

        oldReview.addReviewImages(jpaReviewImageEntities);

    }
}

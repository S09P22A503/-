package com.sshmarket.review.adapter.out.persistence;

import com.sshmarket.review.application.UploadReviewImageService;
import com.sshmarket.review.application.port.out.LoadReviewPort;
import com.sshmarket.review.application.port.out.SaveReviewPort;
import com.sshmarket.review.application.port.out.UpdateReviewPort;
import com.sshmarket.review.common.PersistenceAdapter;
import com.sshmarket.review.domain.Review;
import com.sshmarket.review.domain.ReviewImage;
import com.sshmarket.review.exception.NotFoundException;
import java.util.ArrayList;
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
    public void updateReview(Review modifyReview, List<Long> keepImageIds) {
        JPAReviewEntity oldReview = reviewRepository.findById(modifyReview.getId())
                                                    .orElseThrow(() -> new NotFoundException(
                                                            "리뷰가 존재하지 않습니다."));

        oldReview.setMessage(modifyReview.getMessage());
        oldReview.setStarRating(modifyReview.getStarRating());

        jpaReviewImageRepository.saveAll(
                modifyReview.getReviewImages()
                            .stream()
                            .map(reviewImage ->
                                    JPAReviewImageEntity.from(reviewImage,
                                            oldReview))
                            .collect(Collectors.toList()));

        deleteReviewImages(oldReview, keepImageIds);
    }

    private void deleteReviewImages(JPAReviewEntity oldReview, List<Long> keepImageIds) {
        List<JPAReviewImageEntity> deleteImages = new ArrayList<>();

        List<JPAReviewImageEntity> savedReviewImages = jpaReviewImageRepository.findAllByReview(
                oldReview);

        savedReviewImages.forEach(reviewImage -> {
            if (!keepImageIds.contains(reviewImage.getId())) {
                deleteImages.add(reviewImage);
            }
        });

        jpaReviewImageRepository.deleteAllInBatch(deleteImages);
    }

}

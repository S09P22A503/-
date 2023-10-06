package com.sshmarket.review.adapter.out.persistence;

import com.sshmarket.review.application.dto.ReviewRatingAndNum;
import com.sshmarket.review.application.port.out.LoadReviewPort;
import com.sshmarket.review.common.QueryRepository;
import com.sshmarket.review.domain.Review;
import com.sshmarket.review.exception.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;

@QueryRepository
@RequiredArgsConstructor
class ReviewQueryAdapter implements LoadReviewPort {

    private final JPAReviewRepository jpaReviewRepository;

    private final QueryDslRepository queryDslRepository;

    @Override
    public Review findReviewById(Long id) {
        JPAReviewEntity savedReview = jpaReviewRepository.findById(id)
                                                         .orElseThrow(() -> new NotFoundException(
                                                                 "존재하지 않는 리뷰 ID 입니다."));

        return savedReview.convertToDomain();
    }

    @Override
    public List<Review> findReviewByMemberId(Long memberId) {
        List<JPAReviewEntity> myReviewList = jpaReviewRepository.findAllByMemberId(memberId);

        return myReviewList.stream()
                           .map(JPAReviewEntity::convertToDomain)
                           .toList();
    }

    @Override
    public List<Review> findReviewByArticleId(Long articleId) {
        List<JPAReviewEntity> articleReviews = jpaReviewRepository.findAllByArticleId(articleId);

        return articleReviews.stream()
                             .map(JPAReviewEntity::convertToDomain)
                             .toList();
    }

    @Override
    public List<ReviewRatingAndNum> findAllReviewAvgRatingAndNums(List<Long> articleIds) {
        return queryDslRepository.findAllReviewAvgRatingAndNums(articleIds);
    }
}

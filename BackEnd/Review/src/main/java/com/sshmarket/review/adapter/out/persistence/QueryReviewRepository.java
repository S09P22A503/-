package com.sshmarket.review.adapter.out.persistence;

import com.sshmarket.review.application.port.out.LoadReviewPort;
import com.sshmarket.review.common.QueryRepository;
import com.sshmarket.review.domain.Review;
import com.sshmarket.review.exception.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;

@QueryRepository
@RequiredArgsConstructor
class QueryReviewRepository implements LoadReviewPort {

    private JPAReviewRepository jpaReviewRepository;


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
}

package com.sshmarket.review.adapter.out.persistence;

import com.sshmarket.review.application.port.in.FindReviewQuery;
import com.sshmarket.review.common.QueryRepository;
import com.sshmarket.review.domain.Review;
import java.util.List;
import lombok.RequiredArgsConstructor;

@QueryRepository
@RequiredArgsConstructor
class QueryReviewRepository implements FindReviewQuery {

    private JPAReviewRepository jpaReviewRepository;

    @Override
    public List<Review> findReviewByMemberId() {
//        jpaReviewRepository.find
        return null;
    }
}

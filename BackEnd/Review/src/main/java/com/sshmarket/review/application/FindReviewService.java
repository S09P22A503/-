package com.sshmarket.review.application;

import com.sshmarket.review.application.port.in.FindReviewQuery;
import com.sshmarket.review.domain.Review;
import java.util.List;

public class FindReviewService implements FindReviewQuery {

    @Override
    public List<Review> findReviewByMemberId() {
        return null;
    }
}

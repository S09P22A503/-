package com.sshmarket.review.application.port.out;

import com.sshmarket.review.domain.Review;

public interface InsertReviewPort {

    Review insertReview(Review review);
}

package com.sshmarket.review.application.port.out;

import com.sshmarket.review.domain.Review;
import java.util.List;

public interface LoadReviewPort {

    Review findReviewById(Long id);

    List<Review> findReviewByMemberId(Long memberId);
}

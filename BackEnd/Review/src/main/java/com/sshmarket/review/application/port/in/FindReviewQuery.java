package com.sshmarket.review.application.port.in;

import com.sshmarket.review.domain.Review;
import java.util.List;

public interface FindReviewQuery {

    List<Review> findReviewByMemberId();

}

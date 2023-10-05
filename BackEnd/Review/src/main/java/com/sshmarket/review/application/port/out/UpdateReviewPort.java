package com.sshmarket.review.application.port.out;

import com.sshmarket.review.domain.Review;
import java.util.List;

public interface UpdateReviewPort {

    void updateReview(Review review, List<Long> keepImageIds);

}

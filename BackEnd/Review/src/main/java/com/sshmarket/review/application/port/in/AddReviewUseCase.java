package com.sshmarket.review.application.port.in;

import com.sshmarket.review.application.port.in.command.AddReviewCommand;

public interface AddReviewUseCase {

    boolean addReview(AddReviewCommand addReviewCommand);

}

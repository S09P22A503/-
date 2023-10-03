package com.sshmarket.review.application.port.in;

import com.sshmarket.review.application.port.in.command.AddReviewCommand;

public interface AddReviewUseCase {

    void addReview(AddReviewCommand addReviewCommand);

}

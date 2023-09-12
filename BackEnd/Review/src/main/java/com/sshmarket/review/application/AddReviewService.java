package com.sshmarket.review.application;

import com.sshmarket.review.application.port.in.AddReviewCommand;
import com.sshmarket.review.application.port.in.AddReviewUseCase;
import com.sshmarket.review.application.port.out.InsertImagePort;
import com.sshmarket.review.application.port.out.IntsertReviewPort;
import com.sshmarket.review.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
class AddReviewService implements AddReviewUseCase {

    private final IntsertReviewPort insertReviewPort;
    private final InsertImagePort insertImagePort;

    @Override
    public boolean addReview(AddReviewCommand addReviewCommand) {
        return false;
    }
}

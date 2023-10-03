package com.sshmarket.review.application.port.in;

import com.sshmarket.review.adapter.in.web.request.ReviewModifyRequestDto;
import com.sshmarket.review.application.port.in.command.ModifyReviewCommand;

public interface ModifyReviewUseCase {

    void modifyReview(ModifyReviewCommand modifyReviewCommand);

}

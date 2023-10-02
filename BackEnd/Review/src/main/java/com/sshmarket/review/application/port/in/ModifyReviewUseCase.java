package com.sshmarket.review.application.port.in;

import com.sshmarket.review.adapter.in.web.request.ReviewModifyRequestDto;

public interface ModifyReviewUseCase {

    void modifyReview(ReviewModifyRequestDto reviewModifyRequestDto);

}

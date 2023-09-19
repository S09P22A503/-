package com.sshmarket.review.adapter.in.web;

import com.sshmarket.review.adapter.in.web.request.ReviewAddRequestDto;
import com.sshmarket.review.adapter.in.web.response.HttpResponse;
import com.sshmarket.review.application.port.in.AddReviewUseCase;
import com.sshmarket.review.application.port.in.ModifyReviewUseCase;
import com.sshmarket.review.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final AddReviewUseCase addReviewUseCase;
    private final ModifyReviewUseCase modifyReviewUseCase;

    @PostMapping
    public ResponseEntity<?> reviewAdd(ReviewAddRequestDto reviewAddRequestDto) {
        addReviewUseCase.addReview(reviewAddRequestDto.covertToCommand());
        return HttpResponse.ok(HttpStatus.OK, "리뷰를 등록했습니다.");
    }



}

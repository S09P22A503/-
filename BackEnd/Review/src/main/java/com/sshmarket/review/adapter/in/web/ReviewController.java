package com.sshmarket.review.adapter.in.web;

import com.sshmarket.review.adapter.in.web.jwt.JwtTranslator;
import com.sshmarket.review.application.feign.Member;
import com.sshmarket.review.adapter.in.web.request.ReviewAddRequestDto;
import com.sshmarket.review.adapter.in.web.request.ReviewModifyRequestDto;
import com.sshmarket.review.adapter.in.web.response.HttpResponse;
import com.sshmarket.review.application.port.in.AddReviewUseCase;
import com.sshmarket.review.application.port.in.FindReviewQuery;
import com.sshmarket.review.application.port.in.ModifyReviewUseCase;
import com.sshmarket.review.application.port.in.query.ReviewResponse;
import com.sshmarket.review.common.WebAdapter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    private final FindReviewQuery findReviewQuery;

    private final JwtTranslator jwtTranslator;

    @PostMapping
    public ResponseEntity<?> reviewAdd(ReviewAddRequestDto reviewAddRequestDto) {
        addReviewUseCase.addReview(reviewAddRequestDto.covertToCommand());
        return HttpResponse.ok(HttpStatus.OK, "리뷰를 등록했습니다.");
    }

    @PatchMapping
    public ResponseEntity<?> reviewModify(ReviewModifyRequestDto reviewModifyRequestDto) {
        modifyReviewUseCase.modifyReview(reviewModifyRequestDto.covertToCommand());
        return HttpResponse.ok(HttpStatus.OK, "리뷰를 수정했습니다.");
    }

    @GetMapping
    @RequestMapping("/my-review")
    public ResponseEntity<?> myReviewList(
            @CookieValue(value = "jwt", required = true) String token) {
        Member member = jwtTranslator.translate(token);

        List<ReviewResponse> reviewList = findReviewQuery.findReviewByMemberId(member);

        return HttpResponse.okWithData(HttpStatus.OK, "", reviewList);
    }


}

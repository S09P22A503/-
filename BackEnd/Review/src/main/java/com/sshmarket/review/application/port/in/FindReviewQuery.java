package com.sshmarket.review.application.port.in;

import com.sshmarket.review.application.feign.Member;
import com.sshmarket.review.application.port.in.query.ReviewResponse;
import java.util.List;

public interface FindReviewQuery {

    List<ReviewResponse> findReviewByMemberId(Member member);

}

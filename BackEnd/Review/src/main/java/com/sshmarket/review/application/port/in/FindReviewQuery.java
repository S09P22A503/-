package com.sshmarket.review.application.port.in;

import com.sshmarket.review.application.dto.Member;
import com.sshmarket.review.application.dto.ReviewRatingAndNum;
import com.sshmarket.review.application.dto.ReviewResponse;
import java.util.List;

public interface FindReviewQuery {

    List<ReviewResponse> findReviewByMemberId(Member member);

    List<ReviewResponse> findReviewByArticleId(Long articleId);

    List<ReviewRatingAndNum> findAllReviewAvgRatingAndNums(List<Long> articleIds);
}

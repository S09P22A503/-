package com.sshmarket.review.application;

import com.sshmarket.review.application.feign.Member;
import com.sshmarket.review.application.feign.Article;
import com.sshmarket.review.application.feign.ArticleClientService;
import com.sshmarket.review.application.port.in.FindReviewQuery;
import com.sshmarket.review.application.port.in.query.ReviewResponse;
import com.sshmarket.review.application.port.out.LoadReviewPort;
import com.sshmarket.review.common.UseCase;
import com.sshmarket.review.domain.Review;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
@Transactional
public class FindReviewService implements FindReviewQuery {

    private final LoadReviewPort loadReviewPort;
    private final ArticleClientService articleClientService;

    @Override
    public List<ReviewResponse> findReviewByMemberId(Member member) {
        List<Review> myReviewList = loadReviewPort.findReviewByMemberId(member.getId());

        Map<Long, String> articleInfo = getArticleInfo(myReviewList);

        return myReviewList.stream()
                           .map(myReview ->
                                   ReviewResponse.builder()
                                                 .id(myReview.getId())
                                                 .article(new Article(myReview.getArticleId(),
                                                         articleInfo.get(myReview.getArticleId())))
                                                 .member(member)
                                                 .message(myReview.getMessage())
                                                 .starRating(myReview.getStarRating())
                                                 .createdAt(myReview.getCreatedAt())
                                                 .buyHistoryId(myReview.getBuyHistoryId())
                                                 .images(myReview.getReviewImages())
                                                 .build()
                           )
                           .collect(Collectors.toList());
    }

    private Map<Long, String> getArticleInfo(List<Review> myReviewList) {
        Set<Long> articleIds = new HashSet<>();

        for (Review review : myReviewList) {
            articleIds.add(review.getArticleId());
        }

        return articleClientService.getArticleInfo(
                List.copyOf(articleIds));

    }
}

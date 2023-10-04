package com.sshmarket.review.application;

import com.sshmarket.review.application.dto.Member;
import com.sshmarket.review.application.dto.Article;
import com.sshmarket.review.application.dto.ReviewRatingAndNum;
import com.sshmarket.review.application.feign.ArticleClientService;
import com.sshmarket.review.application.feign.MemberClientService;
import com.sshmarket.review.application.port.in.FindReviewQuery;
import com.sshmarket.review.application.dto.ReviewResponse;
import com.sshmarket.review.application.port.out.LoadReviewPort;
import com.sshmarket.review.common.UseCase;
import com.sshmarket.review.domain.Review;
import java.util.ArrayList;
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
    private final MemberClientService memberClientService;

    @Override
    public List<ReviewResponse> findReviewByMemberId(Member member) {

        List<Review> myReviewList = loadReviewPort.findReviewByMemberId(member.getId());

        List<ReviewResponse> response = new ArrayList<>();

        if (myReviewList != null && myReviewList.size() != 0) {
            Map<Long, String> articleInfo = getArticleInfo(myReviewList);
            response = myReviewList.stream()
                                   .map(myReview ->
                                           ReviewResponse.from(myReview, Article.builder()
                                                                                .id(myReview.getArticleId())
                                                                                .title(articleInfo.get(
                                                                                        myReview.getArticleId()))
                                                                                .build(), member))
                                   .collect(Collectors.toList());
        }

        return response;

    }

    @Override
    public List<ReviewResponse> findReviewByArticleId(Long articleId) {
        List<Review> reviewList = loadReviewPort.findReviewByArticleId(articleId);

        List<ReviewResponse> response = new ArrayList<>();

        if (reviewList != null && reviewList.size() != 0) {

            // member 정보 받아오기
            List<Long> memberIds = getMemberIds(reviewList);
            Map<Long, Member> memberInfo = memberClientService.getMembersInfo(memberIds);

            response = reviewList.stream()
                                 .map(review -> ReviewResponse.from(review, null,
                                         memberInfo.get(review.getMemberId())))
                                 .collect(
                                         Collectors.toList());
        }
        return response;
    }

    @Override
    public List<ReviewRatingAndNum> findAllReviewAvgRatingAndNums(List<Long> articleIds) {
        return loadReviewPort.findAllReviewAvgRatingAndNums(articleIds);
    }

    private List<Long> getMemberIds(List<Review> reviewList) {
        Set<Long> memberIds = new HashSet<>();

        for (Review review : reviewList) {
            memberIds.add(review.getMemberId());
        }

        return List.copyOf(memberIds);
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

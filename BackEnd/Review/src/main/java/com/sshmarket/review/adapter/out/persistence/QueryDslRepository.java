package com.sshmarket.review.adapter.out.persistence;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sshmarket.review.application.dto.ReviewRatingAndNum;
import com.sshmarket.review.common.QueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@QueryRepository
@RequiredArgsConstructor
public class QueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<ReviewRatingAndNum> findAllReviewAvgRatingAndNums(List<Long> articleIds) {
        QJPAReviewEntity reviewEntity = QJPAReviewEntity.jPAReviewEntity;

        return jpaQueryFactory.from(reviewEntity)
                              .groupBy(reviewEntity.articleId)
                              .select(Projections.bean(
                                      ReviewRatingAndNum.class,
                                      reviewEntity.articleId,
                                      reviewEntity.starRating.avg(),
                                      reviewEntity.count()
                              ))
                              .where(reviewEntity.articleId.in(
                                      articleIds))
                              .fetch();
    }

    ;

}

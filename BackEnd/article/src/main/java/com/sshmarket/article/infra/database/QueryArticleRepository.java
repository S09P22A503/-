package com.sshmarket.article.infra.database;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sshmarket.article.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Component
@RequiredArgsConstructor
public class QueryArticleRepository {

    private final JPAQueryFactory jpaQueryFactory;

    QArticle article = QArticle.article;
    QLocation location = QLocation.location;
    QProduct product = QProduct.product;

    // 카테고리, 지역, 거래방식, 검색어
    public Page<Article> searchArticleList(Integer itemId, Long locationId, TradeType tradeType, String keyword, Pageable pageable){
        QueryResults<Article> articleList = jpaQueryFactory.selectDistinct(article)
                .from(article)
                .leftJoin(article.location, location).fetchJoin()
                .leftJoin(article.product, product).fetchJoin()
                .where(productEq(itemId),
                        locationEq(locationId),
                        tradeTypeEq(tradeType),
                        keywordContain(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(article.id.desc())
                .fetchResults();

        return new PageImpl<>(articleList.getResults(), pageable, articleList.getTotal());
    }

    private BooleanExpression productEq(Integer category) {
        return category != null? product.itemId.between(category * 100, (category * 100) + 99) : null;
    }

    private BooleanExpression tradeTypeEq(TradeType tradeType) {
        return tradeType != null? article.tradeType.eq(tradeType) : null;
    }

    private BooleanExpression locationEq(Long locationId) {
        return locationId != null? location.id.eq(locationId) : null;
    }

    private BooleanExpression keywordContain(String keyword){
        if (keyword != null) {
            return article.title.contains(keyword);
        } else {
            return null; // 또는 다른 기본적으로 false를 반환하는 조건
        }
    }
}

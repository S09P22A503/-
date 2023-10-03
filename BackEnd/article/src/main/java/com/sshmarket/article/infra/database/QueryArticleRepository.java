package com.sshmarket.article.infra.database;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sshmarket.article.domain.*;
import lombok.RequiredArgsConstructor;
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
    public List<Article> searchArticleList(Integer itemId, Long locationId, TradeType tradeType, String keyword, Pageable pageable){
        return jpaQueryFactory.select(article)
                .from(article)
                .join(article.location, location).fetchJoin()
                .join(article.product, product).fetchJoin()
                .where(productEq(itemId),
                        locationEq(locationId),
                        tradeTypeEq(tradeType),
                        keywordContain(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

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
        return article.title.contains(keyword);
    }
}

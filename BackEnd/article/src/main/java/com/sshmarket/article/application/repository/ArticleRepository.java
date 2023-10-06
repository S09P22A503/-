package com.sshmarket.article.application.repository;

import com.sshmarket.article.domain.Article;
import com.sshmarket.article.domain.TradeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    Article saveArticle(Article article);

    Optional<Article> findArticleById(Long id);

    Article findArticleDetailById(Long id);

    Page<Article> findArticleListByKeyword(Integer itemId, Long locationId, TradeType tradeType,
                                           String keyword, Pageable pageable);

    List<Article> findArticleByArticleIds(List<Long> articleIds);

    Page<Article> findArticleListByMember(Long memberId, Pageable pageable);

}

package com.sshmarket.article.infra.database;

import com.sshmarket.article.application.repository.ArticleRepository;
import com.sshmarket.article.domain.Article;
import com.sshmarket.article.domain.TradeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {

    private final JpaArticleRepository jpaArticleRepository;
    private final QueryArticleRepository queryArticleRepository;

    @Override
    public Article saveArticle(Article article){
        return jpaArticleRepository.save(article);
    }

    @Override
    public Optional<Article> findArticleById(Long id) {
        return jpaArticleRepository.findById(id);
    }

    @Override
    public Article findArticleDetailById(Long id){
        return jpaArticleRepository.findArticleDetailById(id);
    }

    @Override
    public List<Article> findArticleListByKeyword(Integer itemId, Long locationId, TradeType tradeType,
                                                  String keyword, Pageable pageable){

        return queryArticleRepository.searchArticleList(itemId, locationId, tradeType, keyword, pageable);
    }
}

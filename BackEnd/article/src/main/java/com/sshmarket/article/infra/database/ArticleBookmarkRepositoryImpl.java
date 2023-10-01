package com.sshmarket.article.infra.database;

import com.sshmarket.article.application.repository.ArticleBookmarkRepository;
import com.sshmarket.article.domain.Article;
import com.sshmarket.article.domain.ArticleBookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ArticleBookmarkRepositoryImpl implements ArticleBookmarkRepository {

    private final JpaArticleBookmarkRepository jpaArticleBookmarkRepository;

    @Override
    public ArticleBookmark saveArticleBookmark(ArticleBookmark articleBookmark){
        return jpaArticleBookmarkRepository.save(articleBookmark);
    }

    @Override
    public Boolean isExistArticleBookmark(Article article, Long memberId){
        return jpaArticleBookmarkRepository.existsByArticleAndMemberId(article, memberId);
    }

    @Override
    public void removeArticleBookmark(ArticleBookmark articleBookmark) {
        jpaArticleBookmarkRepository.delete(articleBookmark);
    }

    @Override
    public Optional<ArticleBookmark> findArticleBookmark(Article article, Long memberId) {
        return jpaArticleBookmarkRepository.findByArticleAndMemberId(article, memberId);
    }

}

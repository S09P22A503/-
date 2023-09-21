package com.sshmarket.article.application.repository;

import com.sshmarket.article.domain.Article;

import java.util.Optional;

public interface ArticleRepository {

    Article saveArticle(Article article);

    Optional<Article> findArticleById(Long id);
}

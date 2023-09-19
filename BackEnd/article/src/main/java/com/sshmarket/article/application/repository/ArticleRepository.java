package com.sshmarket.article.application.repository;

import com.sshmarket.article.domain.Article;

public interface ArticleRepository {

    Article save(Article article);
}

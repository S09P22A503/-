package com.sshmarket.article.application.repository;

import com.sshmarket.article.domain.Article;
import com.sshmarket.article.domain.ArticleBookmark;

import java.util.Optional;

public interface ArticleBookmarkRepository {

    ArticleBookmark saveArticleBookmark(ArticleBookmark articleBookmark);

    Boolean isExistArticleBookmark(Article article, Long memberId);

    void removeArticleBookmark(ArticleBookmark articleBookmark);

    Optional<ArticleBookmark> findArticleBookmark(Article article, Long memberId);
}

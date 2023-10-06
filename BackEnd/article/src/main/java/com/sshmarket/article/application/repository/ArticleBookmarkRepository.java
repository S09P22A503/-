package com.sshmarket.article.application.repository;

import com.sshmarket.article.domain.Article;
import com.sshmarket.article.domain.ArticleBookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ArticleBookmarkRepository {

    ArticleBookmark saveArticleBookmark(ArticleBookmark articleBookmark);

    Boolean isExistArticleBookmark(Article article, Long memberId);

    void removeArticleBookmark(ArticleBookmark articleBookmark);

    Optional<ArticleBookmark> findArticleBookmark(Article article, Long memberId);

    Page<ArticleBookmark> findArticleBookmarkByMemberId(Long memberId, Pageable pageable);
}

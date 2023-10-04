package com.sshmarket.article.infra.database;

import com.sshmarket.article.domain.Article;
import com.sshmarket.article.domain.ArticleBookmark;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaArticleBookmarkRepository extends JpaRepository<ArticleBookmark, Long> {

    Boolean existsByArticleAndMemberId(Article article, Long memberId);

    Optional<ArticleBookmark> findByArticleAndMemberId(Article article, Long memberId);

    List<ArticleBookmark> findByMemberId(Long memberId, Pageable pageable);
}

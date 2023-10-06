package com.sshmarket.article.infra.database;

import com.sshmarket.article.domain.Article;
import com.sshmarket.article.domain.ArticleImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface JpaArticleImageRepository extends JpaRepository<ArticleImage, Long> {

    void deleteAllByArticle(Article article);

    void deleteByImageUrl(String imageUrl);

    Optional<ArticleImage> findByImageUrl(String url);
}

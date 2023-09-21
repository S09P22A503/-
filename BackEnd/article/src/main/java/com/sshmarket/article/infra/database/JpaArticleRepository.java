package com.sshmarket.article.infra.database;

import com.sshmarket.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findById(Long id);
}

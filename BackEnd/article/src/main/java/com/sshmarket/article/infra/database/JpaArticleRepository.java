package com.sshmarket.article.infra.database;

import com.sshmarket.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaArticleRepository extends JpaRepository<Article, Long> {
}

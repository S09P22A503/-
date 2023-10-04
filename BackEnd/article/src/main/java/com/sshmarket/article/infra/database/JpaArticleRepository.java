package com.sshmarket.article.infra.database;

import com.sshmarket.article.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findById(Long id);

    @Query("select a from Article a left join a.articleImages left join a.location left join a.product where a.id = :id")
    Article findArticleDetailById(Long id);

    List<Article> findByIdIn(List<Long> idList);

    List<Article> findByMemberId(Long memberId, Pageable pageable);

}

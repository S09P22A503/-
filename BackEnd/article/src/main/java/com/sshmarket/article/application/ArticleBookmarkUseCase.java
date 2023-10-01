package com.sshmarket.article.application;

import com.sshmarket.article.application.repository.ArticleBookmarkRepository;
import com.sshmarket.article.application.repository.ArticleRepository;
import com.sshmarket.article.domain.Article;
import com.sshmarket.article.domain.ArticleBookmark;
import com.sshmarket.article.global.exception.NotFoundResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleBookmarkUseCase {

    private final ArticleBookmarkRepository articleBookmarkRepository;
    private final ArticleRepository articleRepository;

    public void addArticleBookmark(Long articleId, Long memberId){
        Article article = validateArticle(articleId);

        ArticleBookmark articleBookmark = ArticleBookmark.createArticleBookmark(memberId, article);

        articleBookmarkRepository.saveArticleBookmark(articleBookmark);
    }

    public void removeArticleBookmark(Long articleId, Long memberId){
        Article article = validateArticle(articleId);

        ArticleBookmark articleBookmark = articleBookmarkRepository.findArticleBookmark(article, memberId)
                .orElseThrow(() -> new NotFoundResourceException("북마크가 존재하지 않습니다."));

        articleBookmarkRepository.removeArticleBookmark(articleBookmark);
    }

    private Article validateArticle(Long articleId){
        return articleRepository.findArticleById(articleId)
                .orElseThrow(() -> new NotFoundResourceException("존재하지 않는 글입니다."));
    }
}

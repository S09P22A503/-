package com.sshmarket.article.application;

import com.sshmarket.article.application.repository.ArticleImageRepository;
import com.sshmarket.article.application.repository.ArticleRepository;
import com.sshmarket.article.domain.Article;
import com.sshmarket.article.global.exception.NotFoundResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RemoveArticleUseCase {

    private final ArticleRepository articleRepository;

    public void removeArticle(Long id){

        Article article = articleRepository.findArticleById(id)
                .orElseThrow(() -> new NotFoundResourceException("존재하지 않는 글입니다."));

        article.removeArticle(article);
        articleRepository.saveArticle(article);
    }
}

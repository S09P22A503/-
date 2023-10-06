package com.sshmarket.article.application;

import com.sshmarket.article.application.repository.ArticleImageRepository;
import com.sshmarket.article.application.repository.ArticleRepository;
import com.sshmarket.article.domain.Article;
import com.sshmarket.article.global.exception.NotFoundResourceException;
import com.sshmarket.article.global.exception.PermissionDeniedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RemoveArticleUseCase {

    private final ArticleRepository articleRepository;

    public void removeArticle(Long id, Long memberId){
        Article article = articleRepository.findArticleById(id)
                .orElseThrow(() -> new NotFoundResourceException("존재하지 않는 글입니다."));

        validateAuthor(article.getMemberId(), memberId);

        article.removeArticle(article);
        articleRepository.saveArticle(article);
    }

    private void validateAuthor(Long originAuthor, Long author){
        if(originAuthor != author){
            throw new PermissionDeniedException("원글 작성자가 아닙니다.");
        }
    }
}

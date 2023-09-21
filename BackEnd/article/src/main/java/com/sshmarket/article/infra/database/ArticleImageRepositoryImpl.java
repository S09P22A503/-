package com.sshmarket.article.infra.database;

import com.sshmarket.article.application.repository.ArticleImageRepository;
import com.sshmarket.article.domain.Article;
import com.sshmarket.article.domain.ArticleImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ArticleImageRepositoryImpl implements ArticleImageRepository {

    private final JpaArticleImageRepository jpaArticleImageRepository;

    @Override
    public void removeImagesById(Article article){
        jpaArticleImageRepository.deleteAllByArticle(article);
    }

    @Override
    public void removeImageByImageUrl(String url){
        jpaArticleImageRepository.deleteByImageUrl(url);
    }

    @Override
    public Optional<ArticleImage> findImageByUrl(String url){
        return jpaArticleImageRepository.findByImageUrl(url);
    }
}

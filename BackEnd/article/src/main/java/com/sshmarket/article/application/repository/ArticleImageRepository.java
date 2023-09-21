package com.sshmarket.article.application.repository;

import com.sshmarket.article.domain.Article;
import com.sshmarket.article.domain.ArticleImage;

import java.util.Optional;

public interface ArticleImageRepository {

    void removeImagesById(Article article);

    void removeImageByImageUrl(String url);

    Optional<ArticleImage> findImageByUrl(String url);
}

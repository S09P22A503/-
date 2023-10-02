package com.sshmarket.article.application.repository;

import com.sshmarket.article.domain.Article;
import com.sshmarket.article.domain.ArticleImage;

import java.util.List;
import java.util.Optional;

public interface ArticleImageRepository {

    void saveImages(List<ArticleImage> images);

    void removeImagesById(Article article);

    void removeImageByImageUrl(String url);

    Optional<ArticleImage> findImageByUrl(String url);
}

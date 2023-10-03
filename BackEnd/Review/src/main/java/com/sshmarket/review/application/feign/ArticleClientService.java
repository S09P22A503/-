package com.sshmarket.review.application.feign;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleClientService {

    private final ArticleClient articleClient;

    public Map<Long, String> getArticleInfo(List<Long> articleIds) {

        List<Article> articles = articleClient.getArticleNames(List.copyOf(articleIds));

        return articles.stream()
                       .collect(Collectors.toMap(Article::getId,
                               Article::getName));
    }
}

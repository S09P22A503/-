package com.sshmarket.review.application.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "ArticleClient",
        url = "${article.url}"
)
@Component
public interface ArticleClient {

    @GetMapping("/articles/articleName")
    List<Article> getArticleNames(@RequestParam List<Long> articleIds);

}

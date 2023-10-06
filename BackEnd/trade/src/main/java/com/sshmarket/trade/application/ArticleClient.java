package com.sshmarket.trade.application;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "ArticleClient",
        url = "${article.url}"
)
@Component
public interface ArticleClient {

    @GetMapping("/articles/{articleId}")
    ResponseEntity<?> articleDetail(@PathVariable("articleId") Long articleId);

}

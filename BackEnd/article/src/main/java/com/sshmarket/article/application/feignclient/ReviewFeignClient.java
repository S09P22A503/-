package com.sshmarket.article.application.feignclient;

import com.sshmarket.article.dto.ReviewRatingAndNum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "review", url = "${review.url}")
public interface ReviewFeignClient {

    @GetMapping(path = "/reviews/list", produces = "application/json")
    List<ReviewRatingAndNum> getReviewList(@RequestParam List<Long> articleIds);



}

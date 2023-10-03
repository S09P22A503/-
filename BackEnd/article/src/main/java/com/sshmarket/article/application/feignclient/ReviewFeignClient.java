package com.sshmarket.article.application.feignclient;

import com.sshmarket.article.dto.ReviewResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "review", url = "${review.url}")
public interface ReviewFeignClient {

    @PostMapping(path = "/reviews/list", produces = "application/json")
    List<ReviewResponseDto> getMemberList(@RequestBody List<Long> idList);



}

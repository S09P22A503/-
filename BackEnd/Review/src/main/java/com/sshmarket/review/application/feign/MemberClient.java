package com.sshmarket.review.application.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "MemberClient",
        url = "${member.url}"
)
@Component
public interface MemberClient {

    @PostMapping("/members/list")
    ResponseEntity<?> memberList(@RequestBody List<Long> idList);

}

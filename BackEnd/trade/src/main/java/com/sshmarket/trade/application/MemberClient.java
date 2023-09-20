package com.sshmarket.trade.application;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "MemberClient",
        url = "http://localhost:8080"
)
@Component
public interface MemberClient {

    //상대방 정보 가져오기
    @GetMapping("/member")
    ResponseEntity<?> memberDetail(@RequestParam("memberId") Long memberId);
}

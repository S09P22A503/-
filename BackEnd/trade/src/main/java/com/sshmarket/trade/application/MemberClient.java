package com.sshmarket.trade.application;

import com.sshmarket.trade.dto.MemberIdRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "MemberClient",
        url = "${member.url}"
)
@Component
public interface MemberClient {

    //상대방 정보 가져오기
    @GetMapping("/members/{memberId}")
    ResponseEntity<?> memberDetail(@PathVariable("memberId") Long memberId);

    @PostMapping("/members/list")
    ResponseEntity<?> memberList(@RequestBody MemberIdRequestDto memberIdRequestDto);
}

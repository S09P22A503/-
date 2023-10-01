package com.sshmarket.article.application.feignclient;

import com.sshmarket.article.dto.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "member", url = "{member.url}")
@Component
public interface MemberFeignClient {

    @PostMapping("/members/list")
    List<Member> getMemberList(@RequestBody List<Long> idList);

    @GetMapping("/members/{memberId}")
    Member getMember(@PathVariable Long memberId);

}

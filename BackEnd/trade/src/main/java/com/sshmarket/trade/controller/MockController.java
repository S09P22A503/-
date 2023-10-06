package com.sshmarket.trade.controller;

import com.sshmarket.trade.dto.HttpResponse;
import com.sshmarket.trade.dto.MemberIdRequestDto;
import com.sshmarket.trade.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MockController {

    @GetMapping("/members")
    public ResponseEntity<?> memberFind(Long memberId) {
        return HttpResponse.okWithData(HttpStatus.OK, "멤버 조회 성공", new MemberResponseDto(memberId, "수정", "profileImage"));
    }

}
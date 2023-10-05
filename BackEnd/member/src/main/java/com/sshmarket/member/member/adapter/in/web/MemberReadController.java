package com.sshmarket.member.member.adapter.in.web;

import com.sshmarket.member.member.adapter.in.web.dto.MemberIdRequestDto;
import com.sshmarket.member.member.adapter.in.web.dto.MemberInfoResponseDto;
import com.sshmarket.member.member.adapter.in.web.response.HttpResponse;
import com.sshmarket.member.member.application.port.in.ReadMemberUseCase;
import com.sshmarket.member.member.domain.Member;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequiredArgsConstructor
public class MemberReadController {

    private final ReadMemberUseCase readMemberUseCase;

    @GetMapping("/members/{memberId}")
    public ResponseEntity<?> memberDetail(@PathVariable Long memberId) {
         Member member = readMemberUseCase.findMemberById(memberId);
         return HttpResponse.okWithData(HttpStatus.OK, "조회에 성공했습니다." , MemberInfoResponseDto.createFromMember(member));
    }

    @PostMapping("/members/list")
    public ResponseEntity<?> memberList(@RequestBody MemberIdRequestDto memberIdRequestDto) {
        List<Long> idList = memberIdRequestDto.getIdList();
        List<MemberInfoResponseDto> memberInfoResponseDtoList = readMemberUseCase.findMemberListByIdList(idList)
                                                                                 .stream()
                                                                                 .map(member -> MemberInfoResponseDto.createFromMember(member))
                                                                                 .collect(Collectors.toList());
        return HttpResponse.okWithData(HttpStatus.OK, "조회에 성공했습니다.", memberInfoResponseDtoList);
    }

    @GetMapping("/members/all")
    public ResponseEntity<?> memberListAll() {
        List<Long> idList = readMemberUseCase.findMemberList()
                .stream()
                .map(member -> member.getId())
                .collect(Collectors.toList());
        return HttpResponse.okWithData(HttpStatus.OK, "모든 멤버의 아이디 조회 성공", idList);
    }
}

package com.sshmarket.trade.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sshmarket.trade.application.MemberClient;
import com.sshmarket.trade.domain.Trade;
import com.sshmarket.trade.dto.MemberIdRequestDto;
import com.sshmarket.trade.dto.MemberResponseDto;
import com.sshmarket.trade.exception.NotFoundResourceException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberClient memberClient;

    private MemberIdRequestDto getMemberIdRequestDto(List<Trade> trades, Long userId) {
        return new MemberIdRequestDto(
                trades.stream().flatMap(trade -> Stream.of(trade.getSellerId(), trade.getBuyerId()))
                      .filter(id -> !id.equals(userId)).toList());
    }

    private Object getMembersInfo(List<Trade> trades, Long userId) {
        ResponseEntity<?> response = memberClient.memberList(getMemberIdRequestDto(trades, userId));
        return ((LinkedHashMap) response.getBody()).get("data");
    }

    public Map<Long, MemberResponseDto> getMembersMap(List<Trade> trades, Long userId) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> memberResponseList = null;
        try {
            memberResponseList = objectMapper.readValue(
                    objectMapper.writeValueAsString(getMembersInfo(trades, userId)),
                    new TypeReference<List<Map<String, Object>>>() {
                    });
        } catch (JsonProcessingException e) {
            throw new NotFoundResourceException("잘못된 데이터입니다.");
        }

        return memberResponseList.stream().collect(
                Collectors.toMap(map -> ((Integer) map.get("id")).longValue(),
                        map -> objectMapper.convertValue(map, MemberResponseDto.class)));
    }

}

package com.sshmarket.review.application.feign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sshmarket.review.application.dto.Member;
import com.sshmarket.review.exception.NotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberClientService {

    private final MemberClient memberClient;

    public Map<Long, Member> getMembersInfo(List<Long> memberIds) {
        ObjectMapper objectMapper = new ObjectMapper();

        ResponseEntity<?> response = memberClient.memberList(memberIds);

        Object memberInfo = ((LinkedHashMap) response.getBody()).get("data");

        List<Map<String, Object>> memberResponseList = null;
        try {
            memberResponseList = objectMapper.readValue(objectMapper.writeValueAsString(memberInfo),
                    new TypeReference<List<Map<String, Object>>>() {
                    }
            );
        } catch (JsonProcessingException e) {
            throw new NotFoundException("멤버 정보를 찾을 수 없습니다.");
        }
        return Objects.requireNonNull(memberResponseList)
                      .stream()
                      .collect(Collectors.toMap(
                              map -> ((Integer) map.get("id")).longValue(),
                              map -> objectMapper.convertValue(map,
                                      Member.class)));

    }


}

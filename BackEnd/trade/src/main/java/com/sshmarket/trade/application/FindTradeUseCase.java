package com.sshmarket.trade.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sshmarket.trade.application.repository.TradeMessageRepository;
import com.sshmarket.trade.application.repository.TradeRepository;
import com.sshmarket.trade.domain.Status;
import com.sshmarket.trade.domain.Trade;
import com.sshmarket.trade.domain.TradeMessage;
import com.sshmarket.trade.dto.MemberResponseDto;
import com.sshmarket.trade.dto.TradeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindTradeUseCase {

    private final TradeRepository tradeRepository;
    private final TradeMessageRepository tradeMessageRepository;
    private final MemberClient memberClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<TradeResponseDto> findTrades(Long memberId, Status status) {
        List<TradeResponseDto> tradeResponseList = new ArrayList<>();
        List<Trade> trades = tradeRepository.findByMemberIdAndStatus(memberId, status);
        for (Trade trade : trades) {
            //상대방 정보 조회
            Long traderId = trade.findTrader(memberId);
            Object memberResponse = ((LinkedHashMap) memberClient.memberDetail(traderId).getBody()).get("data");
            MemberResponseDto memberResponseDto = null;
            try {
                memberResponseDto = objectMapper.readValue(objectMapper.writeValueAsString(memberResponse), MemberResponseDto.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            //마지막 메시지 조회
            TradeMessage tradeMessage = tradeMessageRepository.findTopByTradeIdOrderByCreatedAtDesc(trade.getId());
            if (tradeMessage != null) {
                tradeResponseList.add(TradeResponseDto.from(trade, tradeMessage, memberResponseDto));
            }
        }

        return tradeResponseList.stream()
                .sorted(Comparator.comparing(TradeResponseDto::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }
}

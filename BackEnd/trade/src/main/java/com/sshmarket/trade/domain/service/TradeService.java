package com.sshmarket.trade.domain.service;

import com.sshmarket.trade.domain.Trade;
import com.sshmarket.trade.domain.TradeMessage;
import com.sshmarket.trade.dto.MemberResponseDto;
import com.sshmarket.trade.dto.TradeSearchResponseDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradeService {

    public List<TradeSearchResponseDto> getTradeSearchResponse(List<Trade> trades, String keyword,
            Map<Long, MemberResponseDto> members, Map<Long, Trade> tradesMap, Long userId,
            List<TradeMessage> latestTradeMessages) {

        List<TradeSearchResponseDto> tradeSearchResponse = new ArrayList<>();
        for (TradeMessage latestTradeMessage : latestTradeMessages) {
            Long tradeId = latestTradeMessage.getTradeId();
            Trade trade = tradesMap.get(tradeId);
            Long audienceId = trade.getSellerId().equals(userId) ? trade.getBuyerId()
                    : trade.getSellerId();
            tradeSearchResponse.add(
                    TradeSearchResponseDto.from(latestTradeMessage, members.get(audienceId)));
        }

        return tradeSearchResponse;
    }

    public List<TradeMessage> getLatestTradeMessages(String keyword,
            List<TradeMessage> allMessages) {
        Map<Long, TradeMessage> latestMessagesMap = new HashMap<>();

        for (TradeMessage message : allMessages) {
            Long tradeId = message.getTradeId();
            TradeMessage existingMessage = latestMessagesMap.getOrDefault(tradeId, null);

            if (existingMessage == null || message.getCreatedAt()
                                                  .isAfter(existingMessage.getCreatedAt())) {
                latestMessagesMap.put(tradeId, message);
            }
        }

        return new ArrayList<>(latestMessagesMap.values());
    }

}

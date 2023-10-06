package com.sshmarket.trade.application;

import com.sshmarket.trade.application.repository.TradeHistoryRepository;
import com.sshmarket.trade.domain.Trade;
import com.sshmarket.trade.domain.TradeHistory;
import com.sshmarket.trade.dto.TradeHistoryCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddTradeHistoryUseCase {

    private final TradeHistoryRepository tradeHistoryRepository;

    public TradeHistory addTradeHistory(Trade trade, TradeHistoryCreateRequestDto tradeHistory) {
        TradeHistory history = TradeHistory.createTradeHistory(trade, tradeHistory.getPrice(),
                tradeHistory.getMainImage(), tradeHistory.getTitle());
        return tradeHistoryRepository.saveTradeHistory(history);
    }
}

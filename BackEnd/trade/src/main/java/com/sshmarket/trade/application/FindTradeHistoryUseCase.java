package com.sshmarket.trade.application;

import com.sshmarket.trade.application.repository.TradeHistoryRepository;
import com.sshmarket.trade.domain.TradeHistory;
import com.sshmarket.trade.dto.TradeHistoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindTradeHistoryUseCase {

    private final TradeHistoryRepository tradeHistoryRepository;

    public Page<TradeHistoryResponseDto> findTradeHistory(Long memberId, Pageable pageable) {
        Page<TradeHistory> tradeHistories = tradeHistoryRepository.findByTradeBuyerId(memberId, pageable);
        return tradeHistories.map(TradeHistoryResponseDto::from);
    }
}

package com.sshmarket.trade.application;

import com.sshmarket.trade.application.repository.TradeMessageRepository;
import com.sshmarket.trade.application.repository.TradeRepository;
import com.sshmarket.trade.domain.Status;
import com.sshmarket.trade.domain.Trade;
import com.sshmarket.trade.domain.TradeMessage;
import com.sshmarket.trade.dto.TradesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindTradeUseCase {

    private final TradeRepository tradeRepository;
    private final TradeMessageRepository tradeMessageRepository;

    public List<TradesResponseDto> findTrades(Long memberId, Status status) {
        List<TradesResponseDto> tradesResponseDto = new ArrayList<>();
        List<Trade> trades = tradeRepository.findByMemberIdAndStatus(memberId, status);
        for (Trade trade : trades) {
            TradeMessage tradeMessage = tradeMessageRepository.findTopByTradeIdOrderByCreatedAtDesc(trade.getId());
            tradesResponseDto.add(TradesResponseDto.from(trade,tradeMessage));
        }
        return tradesResponseDto;
    }
}

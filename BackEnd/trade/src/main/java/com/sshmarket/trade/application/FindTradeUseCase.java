package com.sshmarket.trade.application;

import com.sshmarket.trade.application.repository.TradeRepository;
import com.sshmarket.trade.domain.Status;
import com.sshmarket.trade.domain.Trade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindTradeUseCase {

    private final TradeRepository tradeRepository;

    public List<Trade> findTrades(Long memberId, Status status) {
        List<Trade> trades = tradeRepository.findByMemberIdAndStatus(memberId, status);
        return trades;
    }
}

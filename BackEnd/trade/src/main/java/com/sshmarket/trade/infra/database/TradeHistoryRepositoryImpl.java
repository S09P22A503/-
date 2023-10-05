package com.sshmarket.trade.infra.database;

import com.sshmarket.trade.application.repository.TradeHistoryRepository;
import com.sshmarket.trade.domain.TradeHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TradeHistoryRepositoryImpl implements TradeHistoryRepository {

    private final JpaTradeHistoryRepository jpaTradeHistoryRepository;

    @Override
    public Optional<TradeHistory> findByTradeBuyerId(Long tradeHistoryId) {
        return jpaTradeHistoryRepository.findById(tradeHistoryId);
    }

    @Override
    public Page<TradeHistory> findByTradeBuyerId(Long buyerId, Pageable pageable) {
        return jpaTradeHistoryRepository.findByTradeBuyerId(buyerId, pageable);
    }

    @Override
    public TradeHistory saveTradeHistory(TradeHistory tradeHistory) {
        return jpaTradeHistoryRepository.save(tradeHistory);
    }
}


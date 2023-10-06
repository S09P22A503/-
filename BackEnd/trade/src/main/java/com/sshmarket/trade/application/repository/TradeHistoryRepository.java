package com.sshmarket.trade.application.repository;

import com.sshmarket.trade.domain.TradeHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TradeHistoryRepository {

    Optional<TradeHistory> findByTradeBuyerId(Long tradeHistoryId);

    Page<TradeHistory> findByTradeBuyerId(Long buyerId, Pageable pageable);

    TradeHistory saveTradeHistory(TradeHistory tradeHistory);
}


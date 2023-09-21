package com.sshmarket.trade.application.repository;

import com.sshmarket.trade.domain.TradeHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TradeHistoryRepository {

    Page<TradeHistory> findByTradeBuyerId(Long buyerId, Pageable pageable);

    TradeHistory saveTradeHistory(TradeHistory tradeHistory);
}

